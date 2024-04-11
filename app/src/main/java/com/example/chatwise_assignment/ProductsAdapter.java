package com.example.chatwise_assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_TITLE = 0;
    private static final int VIEW_TYPE_PRODUCT = 1;
    private Context context;
    private List<Product> products;

    public ProductsAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? VIEW_TYPE_TITLE : VIEW_TYPE_PRODUCT;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == VIEW_TYPE_TITLE) {
            View view = inflater.inflate(R.layout.item_title, parent, false);
            return new TitleViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.item_product, parent, false);
            return new ProductViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position > 0) {
            ((ProductViewHolder) holder).bind(products.get(position - 1));
        }
    }

    @Override
    public int getItemCount() {
        return products.size() + 1;
    }

    static class TitleViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;

        public TitleViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
        }

        // Bind title data
        public void bind() {
            titleTextView.setText("Products List");
        }
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        private TextView productNameTextView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTextView = itemView.findViewById(R.id.productNameTextView);
        }

        // Bind product data
        public void bind(Product product) {
            productNameTextView.setText(product.getName());
        }
    }
}
