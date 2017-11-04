

package com.example.lenovo.snake;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


public class BasicView extends View {


    protected static int picSize;

    protected static int xCount;
    protected static int yCount;

    private static int xOff;
    private static int yOff;



    private Bitmap[] pic; 


    private int[][] maps;

    private final Paint mPaint = new Paint();

    public BasicView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Log.i("yang", "BasicView");
        picSize = 10;
    }

    public BasicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i("yang", "BasicView2");
        

        picSize =20;
    }

    
   
    public void resetPic(int tilecount) {
        Log.i("yang", "resetPic");

    	pic = new Bitmap[tilecount];
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.i("yang", "onSizeChanged---w:"+w+"  h:"+h+"  oldw:"+oldw+"  oldh:"+oldh);//480 764 0 0
        xCount = (int) Math.floor(w / picSize);//20
        yCount = (int) Math.floor(h / picSize);

        xOff = ((w - (picSize * xCount)) / 2);
        yOff = ((h - (picSize * yCount)) / 2);

        maps = new int[xCount][yCount];
        Log.i("yang", "xCount:"+xCount+"  yCount:"+yCount);//24 38
        clearMaps();
    }

    public int[] checkTouchPoint(float x, float y) {
        Log.i("yang", "checkTouchPoint x:"+x+"  y:"+y);
        int[] point = new int[0x2];
        point[0x0] = ((int)(x - (float)xOff) / picSize);
        point[0x1] = ((int)(y - (float)yOff) / picSize);
        return point;
    }
    
    public void loadPic(int key, Drawable tile) {
        Log.i("yang", "loadPic key:"+key);

        Bitmap bitmap = Bitmap.createBitmap(picSize, picSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        tile.setBounds(0, 0, picSize, picSize);
        tile.draw(canvas);
        
        pic[key] = bitmap;
    }

    public void clearMaps() {
        Log.i("yang", "clearMaps---xCount:"+xCount+"  yCount:"+yCount);
        //maps = new int[xCount][yCount];
        for (int x = 0; x < xCount; x++) {
            for (int y = 0; y < yCount; y++) {
                setMap(0, x, y);
            }
        }
    }

    public void setMap(int tileindex, int x, int y) {
        //Log.i("yang", "setMap---tileindex:"+tileindex + "--x:" + x + "--y:" + y);
        maps[x][y] = tileindex;
    }

/////////////////////////20160504//////////////////////////
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i("yang", "onDraw");

        for (int x = 0; x < xCount; x += 1) {
            for (int y = 0; y < yCount; y += 1) {
                if (maps[x][y] > 0) {
                    canvas.drawBitmap(pic[maps[x][y]], 
                    		xOff + x * picSize,
                    		yOff + y * picSize,
                    		mPaint);
                    
                    
                   
                }
            }
        }

    }

}
