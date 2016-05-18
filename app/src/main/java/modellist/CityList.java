package modellist;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import activerecordbase.ActiveRecordException;
import common.Const;
import common.NetworkConnectivity;
import model.CityInfo;
import modelmapper.ModelMapHelper;
import myandroi.sampledemo.MyApplication;
import servicehelper.ServiceHelper;
import servicehelper.ServiceResponse;


public class CityList implements
        ServiceHelper.ServiceHelperDelegate {


    private static volatile CityList _instance = null;
    protected ArrayList<CityInfo> m_modelList = null;
    public ModelDelegates.ModelDelegate<CityInfo> m_delegate = null;

    public static CityList Instance() {
        if (_instance == null) {
            synchronized (CityInfo.class) {
                _instance = new CityList();
            }
        }
        return _instance;
    }

    public void GetAllCity(ModelDelegates.ModelDelegate<CityInfo> delegate) {

        // new Task().execute();
        m_delegate = delegate;

        if (m_modelList == null || m_modelList.size() <= 0) {
            if (NetworkConnectivity.isConnected()) {
                ServiceHelper helper = new ServiceHelper(
                        ServiceHelper.GET_ALL_CITY, ServiceHelper.RequestMethod.POST);

                helper.call(this);

            } else {
                if (m_delegate != null)
                    m_delegate
                            .ModelLoadFailedWithError(Const.InterConnectionMsg);
            }

        } else {
            if (m_modelList != null && m_modelList.size() > 0) {
                if (m_delegate != null)
                    m_delegate.ModelLoaded(m_modelList);
            } else {
                if (m_delegate != null)
                    m_delegate
                            .ModelLoadFailedWithError(ServiceHelper.COMMON_ERROR);
            }
        }
    }

    public void ClearDb() {
        try {
            MyApplication.Connection().delete(CityInfo.class);
            m_modelList = null;
        } catch (ActiveRecordException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void CallFinish(ServiceResponse res) {
        if (res.isSuccess) {
            if (res.RawResponse != null && res.RawResponse.length() > 0) {
                try {
                    ClearDb();
                    m_modelList = new ArrayList<CityInfo>();
                    JSONArray list = new JSONArray(res.RawResponse);
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject data = list.getJSONObject(i);

                        if (data != null) {
                            // JSONObject catobj = data.getJSONObject("0");
                            ModelMapHelper<CityInfo> mapper = new ModelMapHelper<CityInfo>();
                            CityInfo info = mapper.getObject(CityInfo.class,
                                    data);
                            if (info != null) {
                                info.save();
                                m_modelList.add(info);
                            }
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (m_delegate != null)
                    m_delegate.ModelLoaded(m_modelList);
            }
        } else {
            if (m_delegate != null)
                m_delegate.ModelLoadFailedWithError(ServiceHelper.COMMON_ERROR);
        }

    }

    @Override
    public void CallFailure(String ErrorMessage) {
        if (m_delegate != null)
            m_delegate.ModelLoadFailedWithError(ErrorMessage);
    }

}
