package tk.sweetvvck.actionbarpulltorefresh;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class DiffuseProgressBar extends View
{
	
	private static final String TAG = "DiffuseProgressBar";
	private Paint mPaint; 
	
	public DiffuseProgressBar(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	}

	@Override
	protected void onVisibilityChanged(View changedView, int visibility)
	{
		super.onVisibilityChanged(changedView, visibility);
		Log.e(TAG, "visibility = " + visibility);
		if (visibility == VISIBLE)
		{
			start();
		}
		else
		{
			stop();
		}
	}
	
	private void stop()
	{
		if (handler != null && runnable != null)
			handler.removeCallbacks(runnable);
	}

	private Handler handler = new Handler();
	
	private Runnable runnable = new Runnable()
	{
		
		@Override
		public void run()
		{
			//
			if (radius > getWidth()/2)
				radius = 10;
			radius += 5;
			//
			invalidate();
			handler.post(runnable);
		}
	};
	
	private void start()
	{
		handler.removeCallbacks(runnable);
		handler.post(runnable);
	}
	
	float radius = 10; 

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		Log.e(TAG, "onDraw()");
		
		mPaint.setColor(Color.BLACK);
		canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
		mPaint.setColor(Color.RED);
		canvas.drawCircle(getWidth()/2, getHeight()/2, radius, mPaint);
	}
}
