package cn.gamedog.xunfaad;

import com.umeng.socialize.utils.Log;

/**
 * 讯发广告对象类
 * 
 * @author admin
 * 
 */
public class XunFaAdData {
	private String appid;
	private String adunitid;
	private String appn;
	private String appv;
	private String im;
	private String sm;
	private String ovs;
	private String brd;
	private String md;
	private String nt;
	private String mc;
	private String ip;
	private String reqt;
	private String postcnt = "1";
	private String lat;
	private String lon;
	private String carrier;
	private String post;
	private String devicetype = "0";
	private String os = "Android";
	private String deviceid;
	private String density;
	private String dvw;
	private String dvh;
	private String orientation = "0";
	private String ua;
	private String usegzip = "false";

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAdunitid() {
		return adunitid;
	}

	public void setAdunitid(String adunitid) {
		this.adunitid = adunitid;
	}

	public String getAppn() {
		return appn;
	}

	public void setAppn(String appn) {
		this.appn = appn;
	}

	public String getAppv() {
		return appv;
	}

	public void setAppv(String appv) {
		this.appv = appv;
	}

	public String getIm() {
		return im;
	}

	public void setIm(String im) {
		this.im = im;
	}

	public String getSm() {
		return sm;
	}

	public void setSm(String sm) {
		this.sm = sm;
	}

	public String getOvs() {
		return ovs;
	}

	public void setOvs(String ovs) {
		this.ovs = ovs;
	}

	public String getBrd() {
		return brd;
	}

	public void setBrd(String brd) {
		this.brd = brd;
	}

	public String getMd() {
		return md;
	}

	public void setMd(String md) {
		this.md = md;
	}

	public String getNt() {
		return nt;
	}

	public void setNt(String nt) {
		this.nt = nt;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getReqt() {
		return reqt;
	}

	public void setReqt(String reqt) {
		this.reqt = reqt;
	}

	public String getPostcnt() {
		return postcnt;
	}

	public void setPostcnt(String postcnt) {
		this.postcnt = postcnt;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getDensity() {
		return density;
	}

	public void setDensity(String density) {
		this.density = density;
	}

	public String getDvw() {
		return dvw;
	}

	public void setDvw(String dvw) {
		this.dvw = dvw;
	}

	public String getDvh() {
		return dvh;
	}

	public void setDvh(String dvh) {
		this.dvh = dvh;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

	public String getUsegzip() {
		return usegzip;
	}

	public void setUsegzip(String usegzip) {
		this.usegzip = usegzip;
	}

	@Override
	public String toString() {

		// private String appid;
		// private String adunitid;
		// private String appn;
		// private String appv;
		// private String im;
		// private String sm;
		// private String ovs;
		// private String brd;
		// private String md;
		// private String nt;
		// private String mc;
		// private String ip;
		// private String reqt;
		// private String postcnt="1";
		// private String lat;
		// private String lon;
		// private String carrier;
		// private String post;
		// private String devicetype="0";
		// private String os="Android";
		// private String deviceid;
		// private String density;
		// private String dvw;
		// private String dvh;
		// private String orientation="0";
		// private String ua;
		// private String usegzip="false";

		String pinjie = "appid=" + appid + "&" + "adunitid=" + adunitid + "&"
				+ "appn=" + appn + "&" + "appv=" + appv + "&" + "im=" + im
				+ "&" + "sm=" + sm + "&" + "ovs=" + ovs + "&" + "brd=" + brd
				+ "&" + "md=" + md + "&" + "nt=" + nt + "&" + "mc=" + mc + "&"
				+ "ip=" + ip + "&" + "reqt=" + reqt + "&" + "postcnt=1&"
				+ "lat=" + lat + "&" + "lon=" + lon + "&" + "carrier="
				+ carrier + "&" + "post=" + post + "&" + "devicetype=0" + "&"
				+ "os=Android&" + "deviceid=" + deviceid + "&" + "density="
				+ density + "&" + "dvw=" + dvw + "&" + "dvh=" + dvh + "&"
				+ "orientation=0&" + "ua=" + ua + "&usegzip=false";

		Log.e("Xunfa",pinjie);
		// TODO Auto-generated method stub
		return pinjie;
	}

}
