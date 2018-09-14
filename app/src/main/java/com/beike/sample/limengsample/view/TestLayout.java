package com.beike.sample.limengsample.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by limeng on 2018/3/19.
 */

public class TestLayout extends FrameLayout {
    public static final int PADDING = 10;
    private List<TagRow> tagRows;

    public TestLayout(@NonNull Context context) {
        super(context);
    }

    public TestLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    public TextView addTextView(String text, int textColor, int textSize) {
        TextView textView = new TextView(getContext());
        textView.setText(text);
        textView.setTextColor(textColor);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        addView(textView);
        return textView;
    }

    public Button addButton(String title, int bgColor, int textColor, int textSize) {
        Button btn = new Button(getContext());
        btn.setText(title);
        btn.setBackgroundColor(bgColor);
        btn.setTextColor(textColor);
        btn.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        addView(btn);
        return btn;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int availWidth = width - getPaddingLeft() - getPaddingRight();

        if (tagRows == null) {
            tagRows = new ArrayList<>();
        }
        tagRows.clear();

        TagRow currentRow = null;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }

            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            if (currentRow == null) {
                currentRow = new TagRow(child, availWidth);
                tagRows.add(currentRow);
            } else {
                if (currentRow.canAdd(child)) {
                    currentRow.addView(child);
                } else {
                    currentRow = new TagRow(child, availWidth);
                    tagRows.add(currentRow);
                }
            }
        }

        int totalHeight = getPaddingTop() + getPaddingBottom();
        for (TagRow row : tagRows) {
            totalHeight += row.rowHeight;
        }
        if (tagRows.size() > 1) {
            totalHeight += PADDING * (tagRows.size() - 1);
        }

        setMeasuredDimension(width, totalHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        super.onLayout(changed, left, top, right, bottom);
        int rowTop = getPaddingTop();
        int leftPadding = getPaddingLeft();
        for (int i = 0; i < tagRows.size(); i++) {
            TagRow row = tagRows.get(i);
            row.onLayout(leftPadding, rowTop);
            rowTop += (row.rowHeight + PADDING);
        }
    }

    public class TagRow {
        List<View> children;
        int rowHeight;
        int extraWidth;

        public TagRow(View v, int totalWidth) {
            children = new ArrayList<>();
            extraWidth = totalWidth;
            addView(v);
        }

        public boolean canAdd(View v) {
            return v.getMeasuredWidth() > extraWidth ? false : true;
        }

        public void addView(View v) {
            children.add(v);
            rowHeight = Math.max(rowHeight, v.getMeasuredHeight());
            extraWidth = Math.max(0, extraWidth - PADDING - v.getMeasuredWidth());
        }

        public void onLayout(int left, int top) {
            int childLeft = left;
            for (int i = 0; i < children.size(); i++) {
                View child = children.get(i);
                int childRight = childLeft + child.getMeasuredWidth();
                int childBottom = top + child.getMeasuredHeight();
                child.layout(childLeft, top, childRight, childBottom);

                childLeft += (child.getMeasuredWidth() + PADDING);
            }
        }
    }
}
