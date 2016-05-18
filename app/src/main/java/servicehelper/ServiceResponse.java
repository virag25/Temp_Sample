package servicehelper;

import org.json.JSONException;
import org.json.JSONObject;

public class ServiceResponse {

	public String RawResponse;
	public boolean isSuccess = false;
	public String Message = "";
	public int Tag = 0;
	public boolean isException = false;
	public int ErrorCode = 0;

	// public org.w3c.dom.Element getRootElement() throws Exception {
	// DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	// DocumentBuilder builder;
	// builder = factory.newDocumentBuilder();
	// InputSource is = new InputSource(new StringReader(RawResponse));
	// org.w3c.dom.Document dom;
	// dom = builder.parse(is);
	// org.w3c.dom.Element root = dom.getDocumentElement();
	// return root;
	// }

	public boolean isSuccess() {
		return isSuccess;
	}

	public String getErrorMessage() {
		try {
			JSONObject main = new JSONObject(RawResponse);
			return main.optString("Message");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "";
	}

	public String GetSuccessMessage() {
		try {
			JSONObject main = new JSONObject(RawResponse);
			// ename=main.getString("name")
			return main.optString("Message");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "";
	}

}
