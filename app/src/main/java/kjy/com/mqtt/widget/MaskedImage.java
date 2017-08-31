package kjy.com.mqtt.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;


public abstract class MaskedImage extends ImageView {

    private static final Xfermode MASK_XFERMODE;
    private Bitmap mask;
    private Paint paint;
    public abstract Bitmap createMask();

    static {
        PorterDuff.Mode localMode = PorterDuff.Mode.DST_IN;
        MASK_XFERMODE = new PorterDuffXfermode(localMode);
    }
    public MaskedImage(Context context) {
        super(context);
    }

    public MaskedImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaskedImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable localDrawable = getDrawable();
        if (localDrawable == null)
            return;
        try {
            if (this.paint == null) {
                Paint localPaint1 = new Paint();
                this.paint = localPaint1;
                this.paint.setFilterBitmap(false);
                Paint localPaint2 = this.paint;
                Xfermode localXfermode1 = MASK_XFERMODE;
                @SuppressWarnings("unused")
                Xfermode localXfermode2 = localPaint2.setXfermode(localXfermode1);
            }
            float f1 = getWidth();
            float f2 = getHeight();
            int i = canvas.saveLayer(0.0F, 0.0F, f1, f2, null, Canvas.CLIP_SAVE_FLAG);
            int j = getWidth();
            int k = getHeight();
            localDrawable.setBounds(0, 0, j, k);
            localDrawable.draw(canvas);
            if ((this.mask == null) || (this.mask.isRecycled())) {
                Bitmap localBitmap1 = createMask();
                this.mask = localBitmap1;
            }
            Bitmap localBitmap2 = this.mask;
            Paint localPaint3 = this.paint;
            canvas.drawBitmap(localBitmap2, 0.0F, 0.0F, localPaint3);
            canvas.restoreToCount(i);
            return;
        } catch (Exception localException) {
        }
    }
}
