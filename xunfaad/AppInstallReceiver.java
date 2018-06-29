package cn.gamedog.xunfaad;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.lidroid.xutils.util.LogUtils;

/**
 * APP状态监听广告
 * 
 * @author admin
 * 
 */
public class AppInstallReceiver extends BroadcastReceiver {
	private static final String TAG = "AppInstallReceiver";
	public static AppInstallReceiver appInstallReceiver = null;

	@Override
	public void onReceive(Context context, Intent intent) {
		final String action = intent.getAction();

		if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(action)) {

		} else if (Intent.ACTION_PACKAGE_REPLACED.equals(action)
				|| Intent.ACTION_PACKAGE_REMOVED.equals(action)
				|| Intent.ACTION_PACKAGE_ADDED.equals(action)) {
			final String packageName = intent.getData().getSchemeSpecificPart();
			final boolean replacing = intent.getBooleanExtra(
					Intent.EXTRA_REPLACING, false);

			if (packageName == null || packageName.length() == 0) {
				return;
			}
			List<String> stringList = new ArrayList<String>();
			stringList.add(packageName);
			if (Intent.ACTION_PACKAGE_REPLACED.equals(action)) {
				// 应用被替换

				Log.i("Xunfa", "替换");
				XunFaAduHelper.sendInstallsucc(packageName);

			} else if (Intent.ACTION_PACKAGE_ADDED.equals(action) && !replacing) {
				Log.i("Xunfa", "安装完成");
				// 应用被添加
				XunFaAduHelper.sendInstallsucc(packageName);
			} else if (Intent.ACTION_PACKAGE_REMOVED.equals(action)
					&& !replacing) {
				// 应用被删除

			}

			// <action
			// android:name="android.intent.action.PACKAGE_INSTALL"></action>
		} else if (Intent.ACTION_PACKAGE_CHANGED.equals(action)) {
			// 改变

		} else if (Intent.ACTION_PACKAGE_RESTARTED.equals(action)) {
			// 重启

		} else if (Intent.ACTION_PACKAGE_INSTALL.equals(action)) {
			Log.i("Xunfa", "安装");
			// 安装
			String packageName = intent.getData().getSchemeSpecificPart();
			XunFaAduHelper.sendInstallstart(packageName);

		}
	}
}
