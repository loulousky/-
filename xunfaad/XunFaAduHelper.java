package cn.gamedog.xunfaad;

import java.io.File;
import java.util.List;

import android.R.transition;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Environment;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Switch;
import cn.gamedog.survivalwarbox.R;
import cn.trinea.android.common.util.PackageUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * 讯发广告
 * 
 * @author admin
 * 
 */
public class XunFaAduHelper {
	/*
	 * 
	 * 初始化
	 */
	private static XunFaAduHelper xunFaAduHelper;
	private static XunFaAdData kaipin;
	private static XunFaAdData banner;
	private static XunFaCallback kaipaicallback;
	public static XunFaCallback getKaipaicallback() {
		return kaipaicallback;
	}

	public static XunFaCallback getBannercallback() {
		return bannercallback;
	}
	private static XunFaCallback bannercallback;
	private static HttpUtils http;
	private static Context application;
	private static String ua;
	public static XunFaAduHelper init(Context context) {

		if (xunFaAduHelper == null) {
			application=context;
			xunFaAduHelper = new XunFaAduHelper();
			ua = new WebView(context).getSettings()
			.getUserAgentString();
			if(kaipin==null){
				ConnectUrlBySplash(context);
			}
			
			if(banner==null){
				ConnectUrlByBanner(context);
			}

		}
		
		http = new HttpUtils(ua);
		http.configCurrentHttpCacheExpiry(0);

		return xunFaAduHelper;
	}
	/**
	 * 
	 * @param context 初始化开屏广告请求信息
	 *            上下文
	 * @param adid
	 *            广告类型ID
	 * 
	 *            private String appid; private String adunitid; private String
	 *            appn; private String appv; private String im; private String
	 *            sm; private String ovs; private String brd; private String md;
	 *            private String nt; private String mc; private String ip;
	 *            private String reqt; private String postcnt = "1"; private
	 *            String lat; private String lon; private String carrier;
	 *            private String post; private String devicetype = "0"; private
	 *            String os = "Android"; private String deviceid; private String
	 *            density; private String dvw; private String dvh; private
	 *            String orientation = "0"; private String ua; private String
	 *            usegzip = "false";
	 */
	private static XunFaAdData ConnectUrlBySplash(Context context) {
		final TelephonyUtils tele = new TelephonyUtils(context);
		TDevice device = new TDevice(context);
		kaipin = new XunFaAdData();
		try {
			// 获取地理位置管理器
			LocationManager locationManager = (LocationManager) context
					.getSystemService(Context.LOCATION_SERVICE);
			// 获取所有可用的位置提供器
			List<String> providers = locationManager.getProviders(true);
			String locationProvider = "";
			if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
				// 如果是GPS
				locationProvider = LocationManager.NETWORK_PROVIDER;
			} else if (providers.contains(LocationManager.GPS_PROVIDER)) {
				// 如果是Network
				locationProvider = LocationManager.GPS_PROVIDER;
			}
			// 获取Location
			Location location = locationManager
					.getLastKnownLocation(locationProvider);
			if (location != null) {

				kaipin.setLat(location.getLatitude() + "");
				kaipin.setLon(location.getLongitude() + "");

			} else {

				kaipin.setLat(0 + "");
				kaipin.setLon(0 + "");
			}

		} catch (Exception e) {
			kaipin.setLat(0 + "");
			kaipin.setLon(0 + "");
		}
		
		try {
			
		
		kaipin.setAppid(XunFaCommon.APPID);
		kaipin.setAppn(tele.getUTF8XMLString(context.getResources().getString(
				R.string.app_name)));
		kaipin.setAppv(device.getVersionCode(context.getPackageName()) + "");
		kaipin.setAdunitid(XunFaCommon.SPLASH);
		kaipin.setBrd(tele.getUTF8XMLString(android.os.Build.BRAND));
		kaipin.setIm(tele.getIMEI());
		kaipin.setSm(tele.getSubscriberId());
		kaipin.setOvs(android.os.Build.VERSION.SDK);
		kaipin.setMd(tele.getUTF8XMLString(android.os.Build.MODEL));
		kaipin.setNt(tele.NetType());
		kaipin.setMc(tele.getMac());
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				kaipin.setIp(tele.GetNetIp());
			}
		}).start();
		kaipin.setReqt(0 + "");
		kaipin.setPostcnt(1 + "");
		kaipin.setCarrier(tele.getProvidersName());
		kaipin.setDevicetype(0 + "");
		kaipin.setOs("Android");
		kaipin.setDeviceid(tele.getIMEI());
		kaipin.setDensity(device.getDensity() + "");
		kaipin.setDvw(((int) device.getScreenWidth()) + "");
		kaipin.setDvh(((int) device.getScreenHeight()) + "");
		kaipin.setOrientation(0 + "");
		kaipin.setUa(tele.getUTF8XMLString(ua));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return kaipin;

	}

	/**
	 * 
	 * @param context 初始化底部横幅广告信息
	 *            上下文
	 * @param adid
	 *            广告类型ID
	 * 
	 *            private String appid; private String adunitid; private String
	 *            appn; private String appv; private String im; private String
	 *            sm; private String ovs; private String brd; private String md;
	 *            private String nt; private String mc; private String ip;
	 *            private String reqt; private String postcnt = "1"; private
	 *            String lat; private String lon; private String carrier;
	 *            private String post; private String devicetype = "0"; private
	 *            String os = "Android"; private String deviceid; private String
	 *            density; private String dvw; private String dvh; private
	 *            String orientation = "0"; private String ua; private String
	 *            usegzip = "false";
	 */
	private static XunFaAdData ConnectUrlByBanner(Context context) {
		final TelephonyUtils tele = new TelephonyUtils(context);
		TDevice device = new TDevice(context);
		banner = new XunFaAdData();
		try {
			// 获取地理位置管理器
			LocationManager locationManager = (LocationManager) context
					.getSystemService(Context.LOCATION_SERVICE);
			// 获取所有可用的位置提供器
			List<String> providers = locationManager.getProviders(true);
			String locationProvider = "";
			if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
				// 如果是GPS
				locationProvider = LocationManager.NETWORK_PROVIDER;
			} else if (providers.contains(LocationManager.GPS_PROVIDER)) {
				// 如果是Network
				locationProvider = LocationManager.GPS_PROVIDER;
			}
			// 获取Location
			Location location = locationManager
					.getLastKnownLocation(locationProvider);
			if (location != null) {

				banner.setLat(location.getLatitude() + "");
				banner.setLon(location.getLongitude() + "");

			} else {

				banner.setLat(0 + "");
				banner.setLon(0 + "");
			}

		} catch (Exception e) {
			banner.setLat(0 + "");
			banner.setLon(0 + "");
		}
		
	try {
		

		banner.setAppid(XunFaCommon.APPID);
		banner.setAppn(tele.getUTF8XMLString(context.getResources().getString(
				R.string.app_name)));
		banner.setAppv(device.getVersionCode(context.getPackageName()) + "");
		banner.setAdunitid(XunFaCommon.BANNER);
		banner.setBrd(tele.getUTF8XMLString(android.os.Build.BRAND));
		banner.setIm(tele.getIMEI());
		banner.setSm(tele.getSubscriberId());
		banner.setOvs(android.os.Build.VERSION.SDK);
		banner.setMd(tele.getUTF8XMLString(android.os.Build.MODEL));
		banner.setNt(tele.NetType());
		banner.setMc(tele.getMac());
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				banner.setIp(tele.GetNetIp());
			}
		}).start();
		banner.setReqt(0 + "");
		banner.setPostcnt(1 + "");
		banner.setCarrier(tele.getProvidersName());
		banner.setDevicetype(0 + "");
		banner.setOs("Android");
		banner.setDeviceid(tele.getIMEI());
		banner.setDensity(device.getDensity() + "");
		banner.setDvw(((int) device.getScreenWidth()) + "");
		banner.setDvh(((int) device.getScreenHeight()) + "");
		banner.setOrientation(0 + "");
		banner.setUa(tele.getUTF8XMLString(ua));
	} catch (Exception e) {
		// TODO: handle exception
	}
		return banner;

	}
	
	
	
	/**
	 * 获取开屏广告信息
	 * 请求返回信息
	 * {"res":"0","data":[{"title":"街机彩金捕鱼","adtype":"download","pn":"com.cygame.jjcjby","vc":111,"vn":"1.0.1",
	 * "landing_url":"http://down.topjoycloud.com//buyu_zzf.apk&mid=1321&snum=p0h102e14t1508392171958s293","sz":"40.50M","ctn":"其它","sctn":"角色扮演","sbt":"经典的捕鱼游戏",
	 * "impr":["http://101.37.225.14:9638/zgapi-outservice2/smonitor?fr=zg&xptp=1&snum=p0h102e14t1508392171958s293"],
	 * "click":["http://101.37.225.14:9638/zgapi-outservice2/smonitor?fr=zg&xptp=2&snum=p0h102e14t1508392171958s293"],
	 * "inst_installsucc":["http://101.37.225.14:9638/zgapi-outservice2/smonitor?fr=zg&xptp=4&snum=p0h102e14t1508392171958s293"],
	 * "inst_downsucc":["http://101.37.225.14:9638/zgapi-outservice2/smonitor?fr=zg&xptp=3&snum=p0h102e14t1508392171958s293"],
	 * "ct":"1","ud":1502783290000,"snum":"p0h102e14t1508392171958s293",
	 * "imgs":[{"type":"iconurl","url":"http://cdn.topjoycloud.com//2RjN6u_IKKJu09aavKWkrw.png","w":0,"h":0},
	 * {"type":"ssu","url":"http://cdn.topjoycloud.com/2sG__MKHa-Kb4W9-hVHc5g.jpg","w":480,"h":320},
	 * {"type":"cp0","url":"http://cdn.topjoycloud.com/2sG__MKHa-Kb4W9-hVHc5g.jpg","w":480,"h":320},
	 * {"type":"cp1","url":"http://cdn.topjoycloud.com/c9f6dvkrv2YLtt7QqCCRDg.jpg","w":1280,"h":200},
	 * {"type":"kp0","url":"http://cdn.topjoycloud.com/Qj_xQBFd2B2VWU4vmtPgyg.jpg","w":480,"h":800},
	 * {"type":"xxl0","url":"http://cdn.topjoycloud.com/2RjN6u_IKKJu09aavKWkrw.png","w":96,"h":96},
	 * {"type":"banner0","url":"http://cdn.topjoycloud.com/c9f6dvkrv2YLtt7QqCCRDg.jpg","w":1280,"h":200},
	 * {"type":"imgurl","url":"http://cdn.topjoycloud.com/c9f6dvkrv2YLtt7QqCCRDg.jpg","w":1280,"h":200}]}]}
	 */
	public void getSplash(final AdCallBackListen listen){
		  TelephonyUtils tele=new TelephonyUtils(application);
		//广告请求URL
		String splashUrl=XunFaCommon.HEAD_URL+kaipin.toString();
		RequestParams params=new RequestParams();
		params.addHeader("ua",tele.getUTF8XMLString(ua));
		Log.i("XunFa", "~~请求UA~~"+splashUrl);

		http.send(HttpRequest.HttpMethod.GET,
				splashUrl,params,
			    new RequestCallBack<String>(){
			        @Override
			        public void onLoading(long total, long current, boolean isUploading) {
			        }

			        @Override
			        public void onSuccess(ResponseInfo<String> responseInfo) {
			        	String aaa=responseInfo.result;
			        	Log.i("Xunfa", aaa);
			        	Gson gson = new GsonBuilder().create();
			        	kaipaicallback=gson.fromJson(aaa, XunFaCallback.class);
			        	/**
			        	 * 获取广告成功
			        	 * 0
			        	 */
			        	if(kaipaicallback!=null&&kaipaicallback.getRes()==0){
			        		try {
			        			XunfaCallBackData callbackdata=kaipaicallback.getData().get(0);
				        		
				        		listen.onImageSuccess(callbackdata);
							} catch (Exception e) {
								// TODO: handle exception
								listen.onfaile();
							}
			        	
			        	}else{
			        		
			        		listen.onfaile();
			        	}
			        	
			        }

			        @Override
			        public void onStart() {
			        }

			        @Override
			        public void onFailure(HttpException error, String msg) {
			        	
			        	listen.onfaile();
			        }
			});
		
		
		
	}
	
	
	/**
	 * 获取开屏广告信息
	 * 请求返回信息
	 * {"res":"0","data":[{"title":"街机彩金捕鱼","adtype":"download","pn":"com.cygame.jjcjby","vc":111,"vn":"1.0.1",
	 * "landing_url":"http://down.topjoycloud.com//buyu_zzf.apk&mid=1321&snum=p0h102e14t1508392171958s293","sz":"40.50M","ctn":"其它","sctn":"角色扮演","sbt":"经典的捕鱼游戏",
	 * "impr":["http://101.37.225.14:9638/zgapi-outservice2/smonitor?fr=zg&xptp=1&snum=p0h102e14t1508392171958s293"],
	 * "click":["http://101.37.225.14:9638/zgapi-outservice2/smonitor?fr=zg&xptp=2&snum=p0h102e14t1508392171958s293"],
	 * "inst_installsucc":["http://101.37.225.14:9638/zgapi-outservice2/smonitor?fr=zg&xptp=4&snum=p0h102e14t1508392171958s293"],
	 * "inst_downsucc":["http://101.37.225.14:9638/zgapi-outservice2/smonitor?fr=zg&xptp=3&snum=p0h102e14t1508392171958s293"],
	 * "ct":"1","ud":1502783290000,"snum":"p0h102e14t1508392171958s293",
	 * "imgs":[{"type":"iconurl","url":"http://cdn.topjoycloud.com//2RjN6u_IKKJu09aavKWkrw.png","w":0,"h":0},
	 * {"type":"ssu","url":"http://cdn.topjoycloud.com/2sG__MKHa-Kb4W9-hVHc5g.jpg","w":480,"h":320},
	 * {"type":"cp0","url":"http://cdn.topjoycloud.com/2sG__MKHa-Kb4W9-hVHc5g.jpg","w":480,"h":320},
	 * {"type":"cp1","url":"http://cdn.topjoycloud.com/c9f6dvkrv2YLtt7QqCCRDg.jpg","w":1280,"h":200},
	 * {"type":"kp0","url":"http://cdn.topjoycloud.com/Qj_xQBFd2B2VWU4vmtPgyg.jpg","w":480,"h":800},
	 * {"type":"xxl0","url":"http://cdn.topjoycloud.com/2RjN6u_IKKJu09aavKWkrw.png","w":96,"h":96},
	 * {"type":"banner0","url":"http://cdn.topjoycloud.com/c9f6dvkrv2YLtt7QqCCRDg.jpg","w":1280,"h":200},
	 * {"type":"imgurl","url":"http://cdn.topjoycloud.com/c9f6dvkrv2YLtt7QqCCRDg.jpg","w":1280,"h":200}]}]}
	 */
	public void getBanner(final AdCallBackListen listen){
		//广告请求URL
		  TelephonyUtils tele=new TelephonyUtils(application);
		String bannerUrl=XunFaCommon.HEAD_URL+banner.toString();
		RequestParams params=new RequestParams();
		params.addHeader("ua",tele.getUTF8XMLString(ua));
		Log.i("Xunfa", "~~~~~~~banner~~~~~~~~~~~~"+bannerUrl);
		http.send(HttpRequest.HttpMethod.GET,
				bannerUrl,params,
			    new RequestCallBack<String>(){
			        @Override
			        public void onLoading(long total, long current, boolean isUploading) {
			        }

			        @Override
			        public void onSuccess(ResponseInfo<String> responseInfo) {
			        	String aaa=responseInfo.result;
			        	Log.i("Xunfa", aaa);
			        	Gson gson = new GsonBuilder().create();
			        	bannercallback=gson.fromJson(aaa, XunFaCallback.class);
			        	/**
			        	 * 获取广告成功
			        	 * 0
			        	 */
			        	if(bannercallback!=null&&bannercallback.getRes()==0){
			        		try {
			        			XunfaCallBackData callbackdata=bannercallback.getData().get(0);
				        		listen.onImageSuccess(callbackdata);
							} catch (Exception e) {
								// TODO: handle exception
								listen.onfaile();
							}
			        		
			        	}else{
			        		
			        		listen.onfaile();
			        	}
			        	
			        }

			        @Override
			        public void onStart() {
			        }

			        @Override
			        public void onFailure(HttpException error, String msg) {
			        	
			        	listen.onfaile();
			        }
			});
		
		
		
	}
	
	
	
	
	
	/**
	 * 循环发送链接
	 * @param array
	 */
	public static void SendArrayBG(String[] array){
       Log.i("Xunfa", "曝光"+array.toString());
       TelephonyUtils tele=new TelephonyUtils(application);
		if(array!=null){
		for (String string : array) {
			Log.i("Xunfa", string);
			RequestParams params=new RequestParams();
			params.addHeader("ua",tele.getUTF8XMLString(ua));
			Log.i("XunFa", "~~~~~~~~~曝光ua~~~~~~~~"+tele.getUTF8XMLString(ua));
			http.send(HttpRequest.HttpMethod.GET,
					string,params,
				    new RequestCallBack<String>(){
				      
				        @Override
				        public void onSuccess(ResponseInfo<String> responseInfo) {
				        	Log.i("Xunfa", responseInfo.statusCode+"~~~~~");
				        }
				        @Override
				        public void onFailure(HttpException error, String msg) {
				        
				        }
				});
		}
		}
	}
	
	
	/**
	 * 循环发送链接
	 * @param array
	 */
	public static void SendArray(String[] array){
       Log.i("Xunfa", "曝光"+array.toString());
       TelephonyUtils tele=new TelephonyUtils(application);
		if(array!=null){
		for (String string : array) {
			
			RequestParams params=new RequestParams();
			params.addHeader("ua", tele.getUTF8XMLString(ua));
			Log.i("XunFa", "~~~~~~~~~曝光ua~~~~~~~~"+tele.getUTF8XMLString(ua));
			http.send(HttpRequest.HttpMethod.GET,
					string,params,
				    new RequestCallBack<String>(){
				      
				        @Override
				        public void onSuccess(ResponseInfo<String> responseInfo) {
				        	
				        }
				        @Override
				        public void onFailure(HttpException error, String msg) {
				        
				        }
				});
		}
		}
	}
	
	/**
	 * 发送点击
	 * @param array
	 * @param x
	 * @param y
	 */
	public static void SendClick(String[] array,float downx,float downy,float upx,float upy){
	    TelephonyUtils tele=new TelephonyUtils(application);
		
		String downxx="IT_CLK_PNT_DOWN_X";
		String downyy="IT_CLK_PNT_DONW_Y";
		String upxx="IT_CLK_PNT_UP_X";
		String upyy="IT_CLK_PNT_UP_Y";
		String time="IT_TS";
		String ime="IT_IMEI";
		
		
		if(array!=null){
	for (String string : array) {
	
             if(string.contains(downxx)){
            	 string.replace(downxx, downx+"");
            	 
             }
             
             if(string.contains(downyy)){
            	 
            	 string.replace(downyy, downy+"");
             }
             
             if(string.contains(upxx)){
            	 string.replace(upxx, upx+"");
             }
             
             if(string.contains(upyy)){
            	 string.replace(upyy,upy+"");
             }
             
             
             if(string.contains(time)){
            	 string.replace(time, System.currentTimeMillis()/1000+"");
             }
             
             if(string.contains(ime)){
            	TelephonyUtils tel=new TelephonyUtils(application);
            	 string.replace(ime, tel.getIMEI()+"");
            	 
             }
             
		
             RequestParams params=new RequestParams();
 			params.addHeader("ua", tele.getUTF8XMLString(ua));
 			Log.i("XunFa", "~~~~~~~~~点击ua~~~~~~~~"+tele.getUTF8XMLString(ua));
			http.send(HttpRequest.HttpMethod.GET,
					string,params,
				    new RequestCallBack<String>(){
				      
				        @Override
				        public void onSuccess(ResponseInfo<String> responseInfo) {
				        	
				        }
				        @Override
				        public void onFailure(HttpException error, String msg) {
				        
				        }
				});
		}
		}
	}
	
	/**
	 * 
	 * 下载
	 */
	public static void Download(final XunfaCallBackData data){

		http.download(data.getLanding_url(),
				 Environment.getExternalStorageDirectory()
					+ "/Survivalcraft/"+data.getTitle()+".apk",
			    true, // 如果目标文件存在，接着未完成的部分继续下载。服务器不支持RANGE时将从新下载。
			    true, // 如果从请求返回信息中获取到文件名，下载完成后自动重命名。
			    new RequestCallBack<File>() {

			        @Override
			        public void onStart() {
			        	//发送下载开始链接
			        	Log.i("Xunfa","下载开始");
			        	SendArray(data.getInst_downstart());
			        }

			        @Override
			        public void onLoading(long total, long current, boolean isUploading) {
			        	
			        	Log.i("Xunfa","下载中"+current);
			        }

			        @Override
			        public void onSuccess(ResponseInfo<File> responseInfo) {
			        	Log.i("Xunfa","下载完成");
			        	//发送下载玩广告链接
			        	SendArray(data.getInst_downsucc());
			        	//跳出安装界面
			        	PackageUtils.installNormal(application, Environment.getExternalStorageDirectory()
					+ "/Survivalcraft/"+data.getTitle()+".apk");
			        }


			        @Override
			        public void onFailure(HttpException error, String msg) {
			        	Log.i("Xunfa","下载失败");
			        }
			});
	}
	
	
	public static void sendInstallstart(String pack){
		
		sendInsta(kaipaicallback,pack);
		sendInsta(bannercallback,pack);
	}
	
	
	
	public static void sendInstallsucc(String pack){
		sendInsuc(kaipaicallback,pack);
		sendInsuc(bannercallback, pack);
		
	}
	
	
	private static void sendInsta(XunFaCallback callbac,String pack){
		try {
			
		
if(callbac!=null){
			
			if(callbac.getData()!=null&&callbac.getData().get(0)!=null){
				  //包名想等发送
				if(pack.equals(callbac.getData().get(0).getPn())){
					
					SendArray(callbac.getData().get(0).getInst_installstart());
				}
			}
		}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private static void sendInsuc(XunFaCallback callbac,String pack){
		
		try {
			
		
if(callbac!=null){
			
			if(callbac.getData()!=null&&callbac.getData().get(0)!=null){
				  //包名想等发送
				if(pack.equals(callbac.getData().get(0).getPn())){
					
					SendArray(callbac.getData().get(0).getInst_installsucc());
				}
			}
		}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
		

}
