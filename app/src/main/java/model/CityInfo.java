package model;


import java.util.ArrayList;
import java.util.List;

import activerecordbase.ActiveRecordBase;
import activerecordbase.ActiveRecordException;
import activerecordbase.CamelNotationHelper;
import modelmapper.ModelMapper;
import myandroi.sampledemo.MyApplication;


public class CityInfo extends ActiveRecordBase {

    @ModelMapper(JsonKey = "city_id")
    public int city_id = 0;
    @ModelMapper(JsonKey = "city_name")
    public String city_name = "";

    public static ArrayList<CityInfo> getAllCity() {
        try {
            List<CityInfo> list = MyApplication.Connection().findAll(
                    CityInfo.class);
            if (list.size() > 0) {
                return (ArrayList<CityInfo>) list;
            }

        } catch (ActiveRecordException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static ArrayList<String> getAllCityName() {
        try {
            List<String> arrCity = MyApplication.Connection()
                    .findDistinctColumn(CityInfo.class, true,
                            CamelNotationHelper.toSQLName("city_name"), null,
                            null);

            if (arrCity != null && arrCity.size() > 0)
                return new ArrayList<String>(arrCity);
        } catch (ActiveRecordException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static void deleteCityInfo() {
        try {
            MyApplication.Connection().delete(CityInfo.class);
        } catch (ActiveRecordException e) {
            e.printStackTrace();
        }
    }

}
