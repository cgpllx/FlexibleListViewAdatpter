package cc.easyandroid.flexiblelistviewadapter;

import android.support.v4.util.ArrayMap;
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
    public static String TAG = EasyFlexibleListViewAdapter.class.getSimpleName();

    public static final int DEFAUL_TVIEWTYPECOUNT = 10;//默认是10

    public final LayoutInflater inflater;

    public OnItemClickListener mItemClickListener;

    public OnItemLongClickListener mItemLongClickListener;

    public static boolean DEBUG = true;

    private int mViewTypeCount;

    public EasyFlexibleListViewAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    private List<T> mItems = new ArrayList<>();

    public void setItems(List<T> items) {
        mItems.clear();
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    /**
     * 必须在设置setAdapter之前才会生效
     *
     * @param viewTypeCount
     */
    public void setViewTypeCount(int viewTypeCount) {
        this.mViewTypeCount = viewTypeCount;
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
    public IFlexible getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {//ItemViewType必须从0开始
        IFlexible item = getItem(position);
        //Map the view type if not done yet
        mapViewTypeFrom(item);
        return mTypeInstances.indexOfKey(item.getLayoutRes());
    }

    @Override
    public boolean isEnabled(int position) {
        return getItem(position).isEnabled();
    }

    private ArrayMap<Integer, IFlexible> mTypeInstances = new ArrayMap<>();


    /**
     * 讲type和 t 映射mTypeInstancesz中
     *
     * @param item item
     */
    private void mapViewTypeFrom(IFlexible item) {
        if (item != null && !mTypeInstances.containsKey(item.getLayoutRes())) {
            mTypeInstances.put(item.getLayoutRes(), item);
            if (DEBUG)
                Log.i(TAG, "Mapped viewType " + item.getLayoutRes() + " from " + item.getClass().getSimpleName());
        }
    }

    @Override
    public int getViewTypeCount() {
        return mViewTypeCount < DEFAUL_TVIEWTYPECOUNT ? DEFAUL_TVIEWTYPECOUNT : mViewTypeCount;
    }

    /**
     * 根据view type 获取对应的对象
     *
     * @param viewType viewType
     * @return T
     */
    private IFlexible getViewTypeInstance(int viewType) {
        return mTypeInstances.get(viewType);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        int viewType = getItemViewType(position);
        IFlexible flexible = getItem(position);
        ViewHolder viewHolder;//|| convertView.getTag(flexible.getLayoutRes()) == null
        if (convertView == null || convertView.getTag(flexible.getLayoutRes()) == null) {
            viewHolder = flexible.createViewHolder(this, inflater, parent);
            convertView = viewHolder.itemView;
            convertView.setTag(flexible.getLayoutRes(), viewHolder);
            Log.i(TAG, "createViewHolder " + flexible.getLayoutRes() + " from " + flexible.getClass().getSimpleName());
        } else {
            viewHolder = (ViewHolder) convertView.getTag(flexible.getLayoutRes());
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
