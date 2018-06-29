android  集成封装 讯发API广告
使用方法，直接通过类

  	
	XunFaAduHelper.init(this).getBanner(new AdCallBackListen() {
			
			@Override
			public void onfaile() {
		
			}
			
			@Override
			public void onImageSuccess(final XunfaCallBackData data) {
				// TODO Auto-generated method stub
				  BitmapUtils bitmapUtils = new BitmapUtils(getApplicationContext());
				  bitmapUtils.configDiskCacheEnabled(true);
				//  bitmapUtils.config
				/**
				 * 下载类
				 */
				if(data.getAdtype().equals("download")){
					
					//获取开屏图片地址
					List<XunFaAdImgs> imgs=data.getImgs();
					for (XunFaAdImgs xunFaAdImgs : imgs) {
						if(xunFaAdImgs.getType().equals("kp0")){
							//图片地址 
						  String imageurl= xunFaAdImgs.getUrl();
						  File file=bitmapUtils.getBitmapFileFromDiskCache(imageurl);
						  
						  
						//  if(file!=null&&file.exists()){
							 
						    bitmapUtils.display(banner, imageurl,new BitmapLoadCallBack<ImageView>() {

								@Override
								public void onLoadCompleted(ImageView arg0,
										String arg1, Bitmap arg2,
										BitmapDisplayConfig arg3,
										BitmapLoadFrom arg4) {
									arg0.setVisibility(View.VISIBLE);
									arg0.setImageBitmap(arg2);
									bannergroup.setVisibility(View.VISIBLE);
								
									//展示完成发送信息
									XunFaAduHelper.SendArray(data.getImpr());
									arg0.setOnTouchListener(new OnTouchListener() {
										private boolean clickone=false;
										private float downx;
										private float downy;
										private float upx;
										private float upy;
										@Override
										public boolean onTouch(View arg0, MotionEvent arg1) {
											switch (arg1.getAction()) {
											 case MotionEvent.ACTION_DOWN:
												//记录按下坐标
												 downx=arg1.getRawX();
												 downy=arg1.getRawY();
										       break;
										     case MotionEvent.ACTION_UP:
										    	 //记录抬起坐标
										    	 upx=arg1.getRawX();
										    	 upy=arg1.getRawY();
										    	 if(!clickone){
											    		ToastUtils.show(getApplicationContext(), "发送发送");
											    	 XunFaAduHelper.SendClick(data.getClick(), downx, downy,upx,upy);
											    	 clickone=true;
											    	 }
										    	 XunFaAduHelper.init(getApplicationContext()).Download(data);
											    break;

											default:
												break;
											}
											
											// TODO Auto-generated method stub
											return true;
										}
									});
									
									
									
									
									
									
									
									
									
									
									// TODO Auto-generated method stub
								}

								@Override
								public void onLoadFailed(ImageView arg0,
										String arg1, Drawable arg2) {
								
								}
							});
//						  }else{
//							  bitmapUtils.display(startadr, imageurl);
//							  jumpani(1500);
//						  }
						}
					}