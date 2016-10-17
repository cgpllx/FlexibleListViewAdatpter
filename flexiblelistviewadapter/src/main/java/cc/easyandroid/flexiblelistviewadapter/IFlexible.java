package cc.easyandroid.flexiblelistviewadapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * 自定义的ViewHolder
 * @param <VH>
 */
public interface IFlexible<VH extends EasyFlexibleListViewAdapter.ViewHolder> {

    /**
     * Returns if the Item is enabled.
     *
     * @return (default) true for enabled item, false for disabled one.
     */
    boolean isEnabled();

    /**
     * Setter to change enabled behaviour.
     *
     * @param enabled false to disable all operations on this item
     */
    void setEnabled(boolean enabled);

	int getLayoutRes();


	VH createViewHolder(EasyFlexibleListViewAdapter adapter, LayoutInflater inflater, ViewGroup parent);


	void bindViewHolder(EasyFlexibleListViewAdapter adapter, VH holder, int position, List payloads);


}