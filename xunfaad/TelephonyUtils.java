package cn.gamedog.xunfaad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.json.JSONObject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.util.Log;

public class TelephonyUtils {

	Context context;
	TelephonyManager tm;

	public TelephonyUtils(Context context) {
		this.context = context;
		tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
	}

	   
	    public static String getUTF8XMLString(String xml) {  
	    // A StringBuffer Object  
	    StringBuffer sb = new StringBuffer();  
	    sb.append(xml);  
	    String xmString = "";  
	    String xmlUTF8="";  
	    try {  
	    xmString = new String(sb.toString().getBytes("UTF-8"));  
	    xmlUTF8 = URLEncoder.encode(xmString, "UTF-8");  
	    System.out.println("utf-8 编码：" + xmlUTF8) ;  
	    } catch (UnsupportedEncodingException e) {  
	    // TODO Auto-generated catch block  
	    e.printStackTrace();  
	    }  
	    // return to String Formed  
	    return xmlUTF8;  
	    } 
	
	
	/*
	 * 唯一的设备ID： GSM手机的 IMEI 和 CDMA手机的 MEID. Return null if device ID is not
	 * available.
	 */
	public String getIMEI() {
		return tm.getDeviceId();
	}

	/*
	 * 电话状态： 1.tm.CALL_STATE_IDLE=0 无活动 2.tm.CALL_STATE_RINGING=1 响铃
	 * 3.tm.CALL_STATE_OFFHOOK=2 摘机
	 */
	public int getCallState() {
		return tm.getCallState();
	}

	/*
	 * 电话方位：
	 */
	public CellLocation getCellLocation() {
		return tm.getCellLocation();
	}

	/*
	 * 设备的软件版本号： 例如：the IMEI/SV(software version) for GSM phones. Return null if
	 * the software version is not available.
	 */
	public String getDeviceSoftwareVersion() {
		return tm.getDeviceSoftwareVersion();
	}

	/*
	 * 手机号： GSM手机的 MSISDN. Return null if it is unavailable.
	 */
	public String getLine1Number() {
		return tm.getLine1Number();
	}

	/*
	 * 附近的电话的信息: 类型：List<NeighboringCellInfo>
	 * 需要权限：android.Manifest.permission#ACCESS_COARSE_UPDATES
	 */
	public List<NeighboringCellInfo> getNeighboringCellInfo() {
		return tm.getNeighboringCellInfo();
	}

	/*
	 * 获取ISO标准的国家码，即国际长途区号。 注意：仅当用户已在网络注册后有效。 在CDMA网络中结果也许不可靠。
	 */
	public String getNetworkCountryIso() {
		return tm.getNetworkCountryIso();
	}

	/*
	 * MCC+MNC(mobile country code + mobile network code) 注意：仅当用户已在网络注册时有效。
	 * 在CDMA网络中结果也许不可靠。
	 */
	public String getNetworkOperator() {
		return tm.getNetworkOperator();

	}

	/*
	 * 按照字母次序的current registered operator(当前已注册的用户)的名字 注意：仅当用户已在网络注册时有效。
	 * 在CDMA网络中结果也许不可靠。
	 */
	public String getNetworkOperatorName() {
		return tm.getNetworkOperatorName();
	}

	/*
	 * 当前使用的网络类型： 例如： NETWORK_TYPE_UNKNOWN 网络类型未知 0 NETWORK_TYPE_GPRS GPRS网络 1
	 * NETWORK_TYPE_EDGE EDGE网络 2 NETWORK_TYPE_UMTS UMTS网络 3 NETWORK_TYPE_HSDPA
	 * HSDPA网络 8 NETWORK_TYPE_HSUPA HSUPA网络 9 NETWORK_TYPE_HSPA HSPA网络 10
	 * NETWORK_TYPE_CDMA CDMA网络,IS95A 或 IS95B. 4 NETWORK_TYPE_EVDO_0 EVDO网络,
	 * revision 0. 5 NETWORK_TYPE_EVDO_A EVDO网络, revision A. 6
	 * NETWORK_TYPE_1xRTT 1xRTT网络 7
	 */
	public int getNetworkType() {
		return tm.getNetworkType();
	}

	/*
	 * 手机类型： 例如： PHONE_TYPE_NONE 无信号 PHONE_TYPE_GSM GSM信号 PHONE_TYPE_CDMA CDMA信号
	 */
	public int getPhoneType() {
		return tm.getPhoneType();
	}

	/*
	 * Returns the ISO country code equivalent for the SIM provider's country
	 * code. 获取ISO国家码，相当于提供SIM卡的国家码。
	 */
	public String getSimCountryIso() {
		return tm.getSimCountryIso();
	}

	/*
	 * Returns the MCC+MNC (mobile country code + mobile network code) of the
	 * provider of the SIM. 5 or 6 decimal digits.
	 * 获取SIM卡提供的移动国家码和移动网络码.5或6位的十进制数字. SIM卡的状态必须是
	 * SIM_STATE_READY(使用getSimState()判断).
	 */
	public String getSimOperator() {
		return tm.getSimOperator();
	}

	/*
	 * 服务商名称： 例如：中国移动、联通 SIM卡的状态必须是 SIM_STATE_READY(使用getSimState()判断).
	 */
	public String getSimOperatorName() {
		return tm.getSimOperatorName();
	}

	/*
	 * SIM卡的序列号： 需要权限：READ_PHONE_STATE
	 */
	public String getSimSerialNumber() {
		return tm.getSimSerialNumber();
	}

	/*
	 * SIM的状态信息： SIM_STATE_UNKNOWN 未知状态 0 SIM_STATE_ABSENT 没插卡 1
	 * SIM_STATE_PIN_REQUIRED 锁定状态，需要用户的PIN码解锁 2 SIM_STATE_PUK_REQUIRED
	 * 锁定状态，需要用户的PUK码解锁 3 SIM_STATE_NETWORK_LOCKED 锁定状态，需要网络的PIN码解锁 4
	 * SIM_STATE_READY 就绪状态 5
	 */
	public int getSimState() {
		return tm.getSimState();
	}

	/*
	 * 唯一的用户ID： 例如：IMSI(国际移动用户识别码) for a GSM phone. 需要权限：READ_PHONE_STATE
	 */
	public String getSubscriberId() {
		if(tm.getSubscriberId()==null){
			return "";
		}else{
		
		return tm.getSubscriberId();
		}
				
				
				
	}

	/*
	 * 取得和语音邮件相关的标签，即为识别符 需要权限：READ_PHONE_STATE
	 */
	public String getVoiceMailAlphaTag() {
		return tm.getVoiceMailAlphaTag();
	}

	/*
	 * 获取语音邮件号码： 需要权限：READ_PHONE_STATE
	 */
	public String getVoiceMailNumber() {
		return tm.getVoiceMailNumber();
	}

	/*
	 * ICC卡是否存在
	 */
	public boolean hasIccCard() {
		return tm.hasIccCard();
	}

	/*
	 * 是否漫游: (在GSM用途下)
	 */
	public boolean isNetworkRoaming() {
		return tm.isNetworkRoaming();
	}

	public String getProvidersName() {
		try {
			String ProvidersName = "-1";
			// 返回唯一的用户ID;就是这张卡的编号神马的
			String IMSI = tm.getSubscriberId();
			// IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
		
			if (IMSI.startsWith("46000")|| IMSI.startsWith("46002") || IMSI.startsWith("46007")) {
				return "0";
			} else if (IMSI.startsWith("46002")) {
				return "46002";
			} else if (IMSI.startsWith("46001") || IMSI.startsWith("46006")) {
				return "1";
			} else if (IMSI.startsWith("46003") || IMSI.startsWith("46005")) {
				return "2";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "-1";
	}

	/**
	 * 获取MAC地址
	 * @return
	 */
	public String getMac() {
		String macSerial = null;
		String str = "";

		try {
			Process pp = Runtime.getRuntime().exec(
					"cat /sys/class/net/wlan0/address ");
			InputStreamReader ir = new InputStreamReader(pp.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);

			for (; null != str;) {
				str = input.readLine();
				if (str != null) {
					macSerial = str.trim();// 去空格
					break;
				}
			}
		} catch (IOException ex) {
			// 赋予默认值
			ex.printStackTrace();
		}
		return macSerial;
	}

	/**
	 * 获取网络状态
	 * @return
	 */
	public String NetType() {
		String netType = "";
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo == null) {
			return netType;
		}
		int nType = networkInfo.getType();
		if (nType == ConnectivityManager.TYPE_MOBILE) {
			if (networkInfo.getExtraInfo().toLowerCase().equals("cmnet")) {
				netType = "cmnet";
			}
			else {
				netType = "cmwap";
			}
		}
		else if (nType == ConnectivityManager.TYPE_WIFI) {
			netType = "wifi";
		}
		return netType;
	}
	
 /**
  * 获取外网IP
  */
	/**
     * 获取外网的IP(要访问Url，要放到后台线程里处理)
     * 
     * @Title: GetNetIp
     * @Description:
     * @param @return
     * @return String
     * @throws
     */
	public static String GetNetIp()  
	{  
	String IP = "";  
	try  
	{  
	String address = "http://pv.sohu.com/cityjson";  
	URL url = new URL(address);  
	HttpURLConnection connection = (HttpURLConnection) url  
	.openConnection();  
	connection.setUseCaches(false);  
	  
	if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)  
	{  
	InputStream in = connection.getInputStream();  
	  
	// 将流转化为字符串  
	BufferedReader reader = new BufferedReader(  
	new InputStreamReader(in));  
	  
	String tmpString = "";  
	StringBuilder retJSON = new StringBuilder();  
	while ((tmpString = reader.readLine()) != null)  
	{  
	retJSON.append(tmpString + "\n");  
	}  
	  
	;
	//retJSON.replace("var returnCitySN = ", "");
	JSONObject jsonObject = new JSONObject(retJSON.toString().replace("var returnCitySN = ", ""));  

	
	IP = jsonObject.getString("cip") ;  
	  
	Log.e("提示", "您的IP地址是：" + IP);  
 
	}  
	else  
	{  
	IP = "";  
	Log.e("提示", "网络连接异常，无法获取IP地址！");  
	}  
	}  
	catch (Exception e)  
	{  
	IP = "";  
	Log.e("提示", "获取IP地址时出现异常，异常信息是：" + e.toString());  
	}  
	return IP;  
	}  
	
	
	
	
	
	
	

}