
package com.example.lenovo.snake;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;


public class Snake extends Activity {

	private static final Button Button = null;
	private SnakeView mSnakeView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("yang", "Snake onCreate");
		setContentView(R.layout.snake_layout);

		mSnakeView = (SnakeView) findViewById(R.id.snake);
		//mSnakeView = new SnakeView(Snake.this, null) ;

		///////////////20160518/////////////////////////////////
		//就是在开始的时候的画面
		mSnakeView.setTextView((TextView) findViewById(R.id.text));

		mSnakeView.setStartButton((Button) findViewById(R.id.start));

		mSnakeView.setControlButton((Button) findViewById(R.id.left), (Button) findViewById(R.id.right),
				(Button) findViewById(R.id.top), (Button) findViewById(R.id.bottom));

		mSnakeView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Log.i("yang", "setOnTouchListener");
				if (mSnakeView.getGameState() == SnakeView.RUNNING) {
					float x = event.getX() / v.getWidth();
					float y = event.getY() / v.getHeight();

					int direction = 0;
					direction = (x > y) ? 1 : 0;
					direction |= (x > 1 - y) ? 2 : 0;

					mSnakeView.onKeyDown(direction, null);

				}

				return false;
			}
		});

		mSnakeView.setMode(SnakeView.READY);
		Log.i("yang", "Snake onCreate end");
	}



	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.i("yang", "onKeyDown keyCode:"+keyCode);

		if(keyCode==KeyEvent.KEYCODE_MENU){
			//在这里就是做你想做的事情
			// 调用这个，就可以弹出菜单
			super.openOptionsMenu();  
		}
		return  true;
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.i("yang", "onStop");
	}

	@Override
	public void onOptionsMenuClosed(Menu menu) {
		//在这里做你想做的事情  
		Log.i("yang", "onOptionsMenuClosed");
		super.onOptionsMenuClosed(menu);
	}

	//添加Menu按钮
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		Log.i("yang", "onCreateOptionsMenu");
		int group1 = 1;        
		int gourp2 = 2;  

		menu.add(group1, 1, 1, "暂停");  
		menu.add(group1, 2, 2, "开始");    
		menu.add(group1, 3, 3, "关于"); 
		return true;   
	}





	//设置按钮的点击事件

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.i("yang", "onCreateOptionsMenu item.getItemId():"+item.getItemId());
		switch (item.getItemId()) {   
		case 1: // do something here  
			mSnakeView.setMode(SnakeView.PAUSE);     
			break;       
		case 2: // do something here       
			mSnakeView.setMode(SnakeView.READY);        
			break;      
		default:   
			return super.onOptionsItemSelected(item);   
		} 
		return true; 
	}
	//设计点击事件 就是点击屏幕Snake做出相应的动作


}
