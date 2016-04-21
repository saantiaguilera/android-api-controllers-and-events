package com.santiago.controllers.recycler_stuff;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * T is the View that will show
 * E is the Entity that represents the view
 *
 * Created by santi on 28/03/16.
 */
public abstract class BaseRecyclerAdapter<T extends View, E> extends RecyclerView.Adapter<BaseRecyclerAdapter.BaseViewHolder> {

    private List<? extends E> dataSet;

    public BaseRecyclerAdapter(@NonNull List<? extends E> dataSet) {
        this.dataSet = dataSet;
    }

    public void setDataSet(@NonNull List<? extends E> dataSet) {
        this.dataSet = dataSet;
    }

    public @NonNull List<? extends E> getDataSet() {
        return dataSet;
    }

    protected abstract @NonNull T createView(@NonNull ViewGroup parent, @NonNull int viewType);
    protected abstract void bindView(@NonNull T t, @NonNull E e);

    @Override
    public BaseRecyclerAdapter.BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(createView(parent, viewType));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(BaseRecyclerAdapter.BaseViewHolder holder, int position) {
        bindView((T) holder.itemView, dataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder {
        public BaseViewHolder(@NonNull T t) {
            super(t);
        }
    }

}
