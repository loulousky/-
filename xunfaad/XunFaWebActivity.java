package cn.gamedog.xunfaad;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import cn.gamedog.survivalwarbox.MainPage;
import cn.gamedog.survivalwarbox.R;

import com.baidu.mobstat.StatService;
import com.umeng.analytics.MobclickAgent;

/**
 * 广告详细页
 * @author mengfanlu
 *
 */
public class XunFaWebActivity extends Activity {
    
	private ImageView btn_back;
	private TextView tv_title;
	private WebView webView;
	private ProgressBar progressBar;
	private String title, weburl;
	private String defaulturl = "http://m.gamedog.cn/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_ad_webview);

		title = getIntent().getExtras().getString("webtitle");
		weburl = getIntent().getExtras().getString("weburl");
		
		initView();
		
	}

	private void initView() {
	    
		btn_back = (ImageView) findViewById(R.id.btn_back);
		tv_title = (TextView) findViewById(R.id.tv_ad_title);
		webView = (WebView) findViewById(R.id.prefecture_webView);
		progressBar = (ProgressBar) findViewById(R.id.prefecture_loading);
		
		tv_title.setText(title);
		webView.getSettings().setJavaScriptEnabled(true);// 启用js
//        webView.getSettings().setUserAgentString(
//                    "gamedog/1.0 (Linux; Android 4.2.1; en-us; Nexus 4 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");

        
      //声明WebSettings子类
        WebSettings webSettings = webView.getSettings();

        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);  

        //支持插件
        webSettings.setPluginState(PluginState.ON); 

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小 
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

//        //缩放操作
//        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
//        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
//        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存 
        webSettings.setAllowFileAccess(true); //设置可以访问文件 
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口 
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        
		
		if(weburl != null && !weburl.equals("")){
		    webView.loadUrl(weburl);
        } else {
            webView.loadUrl(defaulturl);
        }
		webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
          //      webView.loadUrl(url);
        //        return true;
            	 return super.shouldOverrideUrlLoading(view, url);
            }
            @Override
            public void onReceivedSslError(WebView view,
            		SslErrorHandler handler, SslError error) {
            	// TODO Auto-generated method stub
            	  handler.proceed();
            }
         
        });
		
		webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    progressBar.setVisibility(4);
                }
            }
        });
		
		webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent,
                    String contentDisposition, String mimetype,
                    long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
		
		//返回
		btn_back.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
			    if (webView.canGoBack()){
			        webView.goBack();
			    } else {
	                startActivity(new Intent(XunFaWebActivity.this, MainPage.class));
			        finish();
			    }
			}
		});

	}

	/**
     * 监听返回--是否退出程序
     */
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        boolean flag = true;
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {   
            if (webView.canGoBack()){
                webView.goBack();
            } else {
                startActivity(new Intent(XunFaWebActivity.this, MainPage.class));
                finish();
            }
        }
        else
        {
            flag = super.onKeyDown(keyCode, event);
        }
        return flag;
    }

	
	// umeng
	@Override
	public void onResume() {
		super.onResume();
		if (webView != null) {
		    webView.onResume();
        }
		MobclickAgent.onPageStart("GGWebActivity");
		MobclickAgent.onResume(this);
		
		StatService.onResume(this);
	}

	@Override
	public void onPause() {
		super.onPause();
		if (webView != null) {
		    webView.onPause();
        }
		MobclickAgent.onPageEnd("GGWebActivity");
		MobclickAgent.onPause(this);
		
		StatService.onPause(this);
	}
	
}
