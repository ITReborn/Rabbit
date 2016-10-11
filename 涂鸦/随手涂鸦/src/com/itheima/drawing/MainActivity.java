package com.itheima.drawing;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private Paint paint;
	private Canvas canvas;
	private ImageView iv;
	private Bitmap bitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		View black = findViewById(R.id.view_black);
		View bule = findViewById(R.id.view_bule);
		View gray = findViewById(R.id.view_gray);
		View green = findViewById(R.id.view_green);
		View indigo = findViewById(R.id.view_indigo);
		View orange = findViewById(R.id.view_orange);
		View purple = findViewById(R.id.view_purple);
		View red = findViewById(R.id.view_red);
		View yellow = findViewById(R.id.view_yellow);

		bule.setOnClickListener(this);
		black.setOnClickListener(this);
		gray.setOnClickListener(this);
		green.setOnClickListener(this);
		indigo.setOnClickListener(this);
		orange.setOnClickListener(this);
		purple.setOnClickListener(this);
		red.setOnClickListener(this);
		yellow.setOnClickListener(this);

		iv = (ImageView) findViewById(R.id.iv);
		SeekBar sb = (SeekBar) findViewById(R.id.sb);
		
		WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);

        Display display = manager.getDefaultDisplay();

        Point point = new Point();
        display.getSize(point);

        int winWidth = point.x;
        int winHeight = point.y;

		
		//����ͼƬ����
		bitmap = Bitmap.createBitmap(winWidth, winHeight, Bitmap.Config.ARGB_8888);
		//��ʼ�༭
		canvas = new Canvas(bitmap);
		canvas.drawColor(Color.WHITE);

		Matrix matrix = new Matrix();
		paint = new Paint();
		paint.setStrokeWidth(8);

		canvas.drawBitmap(bitmap, matrix, paint);
		iv.setImageBitmap(bitmap);

		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				// ���ñʵĴ�С
				paint.setStrokeWidth(seekBar.getProgress());

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub

			}
		});

		iv.setOnTouchListener(new OnTouchListener() {
			float downX;
			float downY;
			float moveX;
			float moveY;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					downX = event.getX();
					downY = event.getY();
					break;
				case MotionEvent.ACTION_MOVE:
					moveX = event.getX();
					moveY = event.getY();
					canvas.drawLine(downX, downY, moveX, moveY, paint);

					iv.setImageBitmap(bitmap);
					downX = moveX;
					downY = moveY;
					break;
				case MotionEvent.ACTION_UP:

					break;

				default:
					break;
				}

				return true;
			}
		});

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.view_black:
			paint.setColor(Color.BLACK);
			break;
		case R.id.view_bule:
			paint.setColor(Color.BLUE);
			break;
		case R.id.view_gray:
			paint.setColor(Color.GRAY);
			break;
		case R.id.view_green:
			paint.setColor(Color.GREEN);
			break;
		case R.id.view_indigo:
			paint.setColor(0xff00ffff);
			break;
		case R.id.view_orange:
			paint.setColor(0xffff9900);
			break;
		case R.id.view_purple:
			paint.setColor(0xffff00ff);
			break;
		case R.id.view_red:
			paint.setColor(Color.RED);
			break;
		case R.id.view_yellow:
			paint.setColor(Color.YELLOW);
			break;

		default:
			break;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		File file = new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis()
				+ ".jpg");
		if (item.getItemId() == R.id.action_clear) {
			canvas.drawColor(Color.WHITE);
			iv.setImageBitmap(bitmap);
		} else {
			try {
				FileOutputStream stream = new FileOutputStream(file);
				
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
				
				//���͹㲥��ϵͳ����ˢ��ָ��ͼƬ
				Intent intent =new Intent();
				intent.setAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
				
				Uri uri=Uri.fromFile(file);
				intent.setData(uri);
				sendBroadcast(intent);
				Toast.makeText(getApplicationContext(), "����·����"+file.getAbsolutePath(),
						Toast.LENGTH_SHORT).show();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

		return super.onOptionsItemSelected(item);
	}

}
