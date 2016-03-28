package com.theamalgama.controllers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by santi on 28/03/16.
 */
public abstract class GenericRecyclerAdapter<T extends View, E> extends RecyclerView.Adapter<GenericRecyclerAdapter.ViewHolder> {

    private Context context;

    private List<? extends E> dataSet;

    public GenericRecyclerAdapter(Context context, List<? extends E> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public T t;
        public ViewHolder(T t) {
            super(t);
            this.t = t;
        }
    }

    protected abstract T createView(ViewGroup parent, int viewType);
    protected abstract void bindView(T t, E e);

    @Override
    public GenericRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(createView(parent, viewType));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        bindView(holder.t, dataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
