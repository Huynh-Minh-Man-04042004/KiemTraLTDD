package nathja.finalproject.kiemtraltdd.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


import java.util.List;

import nathja.finalproject.kiemtraltdd.R;
import nathja.finalproject.kiemtraltdd.model.Category;

public class GridAdapter extends BaseAdapter {
    private Context context;
    private List<Category> array;

    public GridAdapter(Context context, List<Category> imageUrls) {
        this.context = context;
        this.array = imageUrls;
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int position) {
        return array.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.lastproduct_item, parent, false);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.itemImage);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Category category = array.get(position);
        // Load ảnh từ URL
        Glide.with(context)
                .load(category.getImages())

                .into(holder.imageView);

        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
    }
}