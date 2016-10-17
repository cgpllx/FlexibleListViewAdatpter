package cc.easyandroid.flexiblelistviewadapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用listview适配器
 */
public class EasyFlexibleListViewAdapter<T extends IFlexible> extends BaseAdapter {
    public final LayoutInflater inflater;
    public OnItemClickListener mItemClickListener;
    public OnItemLongClickListener mItemLongClickListener;

    public static boolean DEBUG = true;
    public static String TAG = EasyFlexibleListViewAdapter.class.getSimpleName();

    public EasyFlexibleListViewAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    private List<T> mItems = new ArrayList<>();

    public void setItems(List<T> items) {
        mItems.clear();
        //notifyItemRangeRemoved(getHeaderCount(), oldcount);
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    public boolean addItems(List<T> items) {

        if (items == null || items.isEmpty()) {
            Log.e(TAG, "No items to add!");
            return false;
        }
        //Insert Items
        mItems.addAll(items);
        //Notify range addition
        notifyDataSetChanged();//
        return true;
    }

    public boolean addItem(T item) {

        if (item == null) {
            Log.e(TAG, "No items to add!");
            return false;
        }
        //Insert Items
        mItems.add(item);
        //Notify range addition
        notifyDataSetChanged();//
        return true;
    }

    public void clearItems() {
        mItems.clear();
        notifyDataSetChanged();
    }

    public void remove(int position) {
        this.mItems.remove(position);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public T getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getLayoutRes();
    }

    @Override
    public boolean isEnabled(int position) {
        return getItem(position).isEnabled();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        T flexible = mItems.get(position);
        ViewHolder viewHolder;
        if (convertView == null || convertView.getTag() == null) {
            viewHolder = flexible.createViewHolder(this, inflater, parent);
            convertView = viewHolder.itemView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.setRefreshPosition(position);
        flexible.bindViewHolder(this, viewHolder, position, null);
        return convertView;
    }

    public static abstract class ViewHolder {
        public final View itemView;
        public int position;

        public void setRefreshPosition(int position) {
            this.position = position;
        }

        public ViewHolder(View itemView) {
            if (itemView == null) {
                throw new IllegalArgumentException("itemView may not be null");
            }
            this.itemView = itemView;
        }
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {

        this.mItemClickListener = itemClickListener;

    }

    public void setItemLongClickListener(OnItemLongClickListener itemLongClickListener) {

        this.mItemLongClickListener = itemLongClickListener;

    }

    public interface OnItemClickListener {

        boolean onItemClick(int position);

    }

    public interface OnItemLongClickListener {

        void onItemLongClick(int position);

    }
}
