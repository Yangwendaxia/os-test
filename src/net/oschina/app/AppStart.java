package net.oschina.app;

import net.oschina.app.common.StringUtils;
import net.oschina.app.ui.Main;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

/**
 * 应用程序启动类：显示欢迎界面并跳转到主界面
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
public class AppStart extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		final View view = View.inflate(this, R.layout.start, null);
		setContentView(view);
		// 渐变展示启动屏
		AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
		aa.setDuration(2000);
		view.startAnimation(aa);
		aa.setAnimationListener(new AnimationListener()
		{
			@Override
			public void onAnimationEnd(Animation arg0)
			{
				redirectTo();
			}

			@Override
			public void onAnimationRepeat(Animation animation)
			{
			}

			@Override
			public void onAnimationStart(Animation animation)
			{
			}
		});
		// 兼容低版本cookie（1.5版本以下，包括1.5.0,1.5.1）
		AppContext appContext = (AppContext) getApplication();
		String cookie = appContext.getProperty("cookie");
		//例:oscid=PR7FwfDo%2F%2BLgKYItYNcGB4iXdL2F8MQgfSf7kVY4H3xQVpuay59OfmoGsblVWtI%2B%2FBG8Sex7Lbvex5K9FgVGFbbbLQoM8%2B2zVIPiJsmngZEsE6qviW9rKw%3D%3D;
		if (StringUtils.isEmpty(cookie))//初次启动/注销后Cookie为空
		{
			String cookie_name = appContext.getProperty("cookie_name");
			String cookie_value = appContext.getProperty("cookie_value");
			if (!StringUtils.isEmpty(cookie_name) && !StringUtils.isEmpty(cookie_value))
			{
				cookie = cookie_name + "=" + cookie_value;
				appContext.setProperty("cookie", cookie);
				appContext.removeProperty("cookie_domain", "cookie_name", "cookie_value", "cookie_version", "cookie_path");
			}
		}
	}

	/**
	 * 跳转到...
	 */
	private void redirectTo()
	{
		Intent intent = new Intent(this, Main.class);
		startActivity(intent);
		finish();
	}
}