package com.liefery.android.icon_badge;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.liefery.android.icon_badge.drawer.background.BackgroundProvider;
import com.liefery.android.icon_badge.drawer.background.PinBackgroundProvider;
import com.liefery.android.icon_badge.drawer.background.CircleBackgroundProvider;
import com.liefery.android.icon_badge.drawer.background.SquareBackgroundProvider;
import com.liefery.android.icon_badge.drawer.foreground.ForegroundShapeDrawer;

public class IconBadgeView extends View implements IconBadgeable {
    private final IconBadge iconBadge = new IconBadge( getContext() );

    private int size;

    private BackgroundProvider.Result backgroundProviderResult;

    public IconBadgeView( Context context ) {
        super( context );

        TypedArray styles = context
                        .obtainStyledAttributes( R.styleable.IconBadgeView );
        initialize( styles );
        styles.recycle();
    }

    public IconBadgeView( Context context, AttributeSet attrs ) {
        super( context, attrs );

        TypedArray styles = context.obtainStyledAttributes(
            attrs,
            R.styleable.IconBadgeView );
        initialize( styles );
        styles.recycle();
    }

    public IconBadgeView( Context context, AttributeSet attrs, int defStyleAttr ) {
        super( context, attrs, defStyleAttr );

        TypedArray styles = context.obtainStyledAttributes(
            attrs,
            R.styleable.IconBadgeView,
            defStyleAttr,
            0 );
        initialize( styles );
        styles.recycle();
    }

    @TargetApi( Build.VERSION_CODES.LOLLIPOP )
    public IconBadgeView(
        Context context,
        AttributeSet attrs,
        int defStyleAttr,
        int defStyleRes ) {
        super( context, attrs, defStyleAttr, defStyleRes );

        TypedArray styles = context.obtainStyledAttributes(
            attrs,
            R.styleable.IconBadgeView,
            defStyleAttr,
            defStyleRes );
        initialize( styles );
        styles.recycle();
    }

    private void initialize( @NonNull TypedArray styles ) {
        // setLayerType( View.LAYER_TYPE_SOFTWARE, null );

        int backgroundShapeColor = styles.getColor(
            R.styleable.IconBadgeView_iconBadge_backgroundShapeColor,
            Integer.MIN_VALUE );
        if ( backgroundShapeColor != Integer.MIN_VALUE )
            setBackgroundShapeColor( backgroundShapeColor );

        int foregroundShape = styles.getInt(
            R.styleable.IconBadgeView_iconBadge_foregroundShape,
            -1 );
        if ( foregroundShape == 0 )
            setShapeArrowUp();
        else if ( foregroundShape == 1 )
            setShapeArrowDown();

        int backgroundShape = styles.getInt(
            R.styleable.IconBadgeView_iconBadge_backgroundShape,
            0 );
        switch ( backgroundShape ) {
            case 0:
                setBackgroundProvider( new CircleBackgroundProvider() );
            break;
            case 1:
                setBackgroundProvider( new SquareBackgroundProvider() );
            break;
            case 2:
                setBackgroundProvider( new PinBackgroundProvider() );
            break;
        }

        int foregroundShapeColor = styles.getColor(
            R.styleable.IconBadgeView_iconBadge_foregroundShapeColor,
            Integer.MIN_VALUE );
        if ( foregroundShapeColor != Integer.MIN_VALUE )
            setForegroundShapeColor( foregroundShapeColor );

        int number = styles.getInt(
            R.styleable.IconBadgeView_iconBadge_number,
            -1 );
        if ( number != -1 )
            setNumber( number );

        int foregroundDrawableResId = styles.getResourceId(
            R.styleable.IconBadgeView_iconBadge_foregroundShapeDrawable,
            -1 );
        if ( foregroundDrawableResId != -1 ) {
            Drawable drawable = ContextCompat.getDrawable(
                getContext(),
                foregroundDrawableResId );
            setForegroundDrawable( drawable );
        }
    }

    @Override
    public void setBackgroundShapeColor( @ColorInt int color ) {
        iconBadge.setBackgroundShapeColor( color );
        invalidate();
    }

    @Override
    public void setForegroundShapeColor( @ColorInt int color ) {
        iconBadge.setForegroundShapeColor( color );
        invalidate();
    }

    @Override
    public void setShapeArrowUp() {
        iconBadge.setShapeArrowUp();
        invalidate();
    }

    @Override
    public void setShapeArrowDown() {
        iconBadge.setShapeArrowDown();
        invalidate();
    }

    @Override
    public void setForegroundDrawable( Drawable drawable ) {
        iconBadge.setForegroundDrawable( drawable );
        invalidate();
    }

    @Override
    public void setNumber( int number ) {
        iconBadge.setNumber( number );
        invalidate();
    }

    @Override
    public void setForegroundDrawer( ForegroundShapeDrawer drawer ) {
        iconBadge.setForegroundDrawer( drawer );
        invalidate();
    }

    @Override
    public int getBackgroundShapeColor() {
        return iconBadge.getBackgroundShapeColor();
    }

    @Override
    public int getForegroundShapeColor() {
        return iconBadge.getForegroundShapeColor();
    }

    //    @Override
    //    public float getElevation() {
    //        return iconBadge.getElevation();
    //    }
    //
    //    @Override
    //    public void setElevation( float elevation ) {
    //        iconBadge.setElevation( elevation );
    //        invalidate();
    //    }

    @Override
    public BackgroundProvider getBackgroundProvider() {
        return iconBadge.getBackgroundProvider();
    }

    @Override
    public void setBackgroundProvider( BackgroundProvider provider ) {
        iconBadge.setBackgroundProvider( provider );
        invalidate();
    }

    @Override
    protected void onDraw( Canvas canvas ) {
        iconBadge.draw( canvas, size, backgroundProviderResult );
    }

    @Override
    protected void onSizeChanged(
        int width,
        int height,
        int oldWeight,
        int oldHeight ) {
        super.onSizeChanged( width, height, oldWeight, oldHeight );

        size = Math.min( width, height );

        backgroundProviderResult = iconBadge.getBackgroundProvider().export(
            size,
            0 );

        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {
            ViewOutlineProvider outline = iconBadge.getBackgroundProvider()
                            .export( size, 0 ).outline;
            setOutlineProvider( outline );
        }
    }
}
