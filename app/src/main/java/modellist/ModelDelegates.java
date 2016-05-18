package modellist;

import java.util.ArrayList;

import activerecordbase.ActiveRecordBase;
import servicehelper.ServiceResponse;


public class ModelDelegates {

	public interface LoginDelegate {
		public void LoginDidSuccess();

		public void LoginFailedWithError(String error);
	}

	public interface LogoutDelegate {
		public void LogoutDidSuccess();

		public void LogoutFailedWithError(String error);
	}

	public interface ModelDelegate<T extends ActiveRecordBase> {
		public void ModelLoaded(ArrayList<T> list);

		public void ModelLoadFailedWithError(String error);

	}
	
	public interface CommonDelegate1 {
		public void CallDidSuccess(String msg);

		public void CallFailedWithError(String error);
	}

	public interface LoadModelDelegate<T> {
		public void ModelLoaded(T obj);

		public void ModelLoadFailedWithError(String error);

	}

	public interface SaveDataDelegate<T> {
		public void DataSavedSuccessfully(T obj);

		public void DataSavedFailedWithError(String error);
	}

	public interface SaveMcqDelegate {
		public void SaveMcqSuccessfully(String success);

		public void SaveMcqFailedWithError(String error);
	}

	public interface CallDelegate {
		public void CallSuccessfully(ServiceResponse res);

		public void CallFailedWithError(String error);
	}

	public interface UniverselDelegate {
		public void CallSuccessfully(ServiceResponse res);

		public void CallFailedWithError(String error);
	}

	public interface VersionChecklDelegate {
		public void CallSuccessfully(int code, String version_name,
									 boolean ForceUpdate);

		public void CallFailedWithError(String error);
	}

	public interface RegisterDelegate {
		public void RegisterSuccessfully(String success);

		public void RegisterFailedWithError(String error);
	}

	public interface CommonDelegate {
		public void CallDidSuccess(boolean flag);

		public void CallFailedWithError(String error);
	}

	public interface UniversalDelegate {
		public void CallDidSuccess(ServiceResponse res);

		public void CallFailedWithError(String error);
	}
}
