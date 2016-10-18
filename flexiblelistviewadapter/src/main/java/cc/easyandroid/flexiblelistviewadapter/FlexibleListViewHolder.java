package cc.easyandroid.flexiblelistviewadapter;

import android.util.Log;
import android.view.View;

/**
 * 一个抽象的holder，实现了一些简单的功能
 */
public abstract class FlexibleListViewHolder extends EasyFlexibleListViewAdapter.ViewHolder
        implements View.OnClickListener, View.OnLongClickListener {

    private static final String TAG = FlexibleListViewHolder.class.getSimpleName();

    //EasyFlexibleAdapter is needed to retrieve listeners and item status
    protected final EasyFlexibleListViewAdapter mAdapter;
    private View contentView;

    public FlexibleListViewHolder(View view, EasyFlexibleListViewAdapter adapter) {
        super(view);
        contentView = view;
        this.mAdapter = adapter;
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);

    }

    public View getContentView() {
        return contentView != null ? contentView : itemView;
    }

    public void onClick(View view) {
        if (!mAdapter.isEnabled(position)) return;
        //Experimented that, if LongClick is not consumed, onClick is fired. We skip the
        //call to the listener in this case, which is allowed only in ACTION_STATE_IDLE.
        if (mAdapter.mItemClickListener != null) {
            if (EasyFlexibleListViewAdapter.DEBUG)
                Log.v(TAG, "onClick on position " + position);
            //Get the permission to activate the View from user
            mAdapter.mItemClickListener.onItemClick(position);


        }
    }

    public boolean onLongClick(View view) {
        if (!mAdapter.isEnabled(position)) return false;
        if (EasyFlexibleListViewAdapter.DEBUG)
            Log.v(TAG, "onLongClick on position " + position);
        //If DragLongPress is enabled, then LongClick must be skipped and the listener will
        // be called in onActionStateChanged in Drag mode.
        if (mAdapter.mItemLongClickListener != null) {
            mAdapter.mItemLongClickListener.onItemLongClick(position);
            return true;
        }
        return false;
    }



}