package com.itheima.color;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        SeekBar sb = (SeekBar) findViewById(R.id.sb);
        final ImageView iv = (ImageView) findViewById(R.id.iv);
        //�ҵ�ԭͼ
        final Bitmap srcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a1);
        
        //���Ƹ���
        final Bitmap copyBitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), srcBitmap.getConfig());
        
        //��ʼ�༭
        final Canvas canvas=new Canvas(copyBitmap);
        
        final Matrix matrix=new Matrix();
		final Paint paint=new Paint();
		canvas.drawBitmap(srcBitmap, matrix, paint);
        
        
        //����seekbar������ɫ
       
		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				int progress = seekBar.getProgress();
				float val = progress / 50.0f;// val ֵ 0 ~ 1 ~ 2
				//val 0~1 0.5 ƫ��
				//val 1~2 1.5 ƫ��
				
				//��ɫ���飬��ɫ��
				ColorMatrix cm = new ColorMatrix();
				cm.set(new float[] {
				1* val , 0, 0, 0, 0,	//��ɫR
				0, 1, 0, 0, 0,	//��ɫG
				0, 0, 1, 0, 0,	//��ɫB
				0, 0, 0, 1, 0	//͸����A
				});
				paint.setColorFilter(new ColorMatrixColorFilter(cm));
				
				canvas.drawBitmap(srcBitmap, matrix, paint);
				iv.setImageBitmap(copyBitmap);
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				
				float val = progress / 50.0f;// val ֵ 0 ~ 1 ~ 2
				//val 0~1 0.5 ƫ��
				//val 1~2 1.5 ƫ��
				
				//��ɫ���飬��ɫ��
				ColorMatrix cm = new ColorMatrix();
				cm.set(new float[] {
				1* val , 0, 0, 0, 0,	//��ɫR
				0, 1, 0, 0, 0,	//��ɫG
				0, 0, 1, 0, 0,	//��ɫB
				0, 0, 0, 1, 0	//͸����A
				});
				paint.setColorFilter(new ColorMatrixColorFilter(cm));
				
				canvas.drawBitmap(srcBitmap, matrix, paint);
				iv.setImageBitmap(copyBitmap);
				
				
			}
		});
		
		
		//��ͼд��iv
        
        iv.setImageBitmap(copyBitmap);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
