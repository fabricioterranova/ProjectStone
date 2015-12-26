package com.fabriciooliveira.projectstone.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fabriciooliveira.projectstone.CreditCardActivity;
import com.fabriciooliveira.projectstone.R;
import com.fabriciooliveira.projectstone.domain.Product;

import java.util.ArrayList;

/**
 * Created by fabriciooliveira on 12/25/15.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolderProduct>{

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<Product> mListProducts = new ArrayList<Product>();

    public ProductAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);

        populateMockProducts();
    }

    public void populateMockProducts() {

        Product product1 = new Product("Conjunto de Xicaras", 1, "Cozinha", (float) 10.99, R.mipmap.cup);
        Product product2 = new Product("Conjunto de Vasos", 1, "Cozinha", (float) 15.99, R.mipmap.msvases);
        Product product3 = new Product("Vaso formato Xicara", 1, "Mesa", (float) 20.99, R.mipmap.xicaras);
        Product product4 = new Product("Sapato Salto Alto", 1, "Vestuario", (float) 25.99, R.mipmap.shoes);

        mListProducts.add(product1);
        mListProducts.add(product2);
        mListProducts.add(product3);
        mListProducts.add(product4);

    }

    @Override
    public ViewHolderProduct onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.product_line, parent, false);
        ViewHolderProduct viewHolderProduct = new ViewHolderProduct(view);

        return viewHolderProduct;
    }

    @Override
    public void onBindViewHolder(ViewHolderProduct holder, final int position) {
        final Product currentProduct = mListProducts.get(position);

        holder.mProductThumbnail.setImageResource(currentProduct.getIdImagem());
        holder.mTitle.setText(currentProduct.getTitle());
        holder.mQuantity.setText("Quantidade: " + String.valueOf(currentProduct.getQuantity()));
        holder.mCategory.setText("Categoria: " + currentProduct.getCategory());
        holder.mValue.setText("R$ " + String.valueOf(currentProduct.getValue()));

        holder.mButtonBuy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CreditCardActivity.class);
                intent.putExtra("product", mListProducts.get(position));
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListProducts.size();
    }

    static class ViewHolderProduct extends RecyclerView.ViewHolder{

    private ImageView mProductThumbnail;
    private TextView mTitle;
    private TextView mQuantity;
    private TextView mCategory;
    private TextView mValue;
    private Button mButtonBuy;

    public ViewHolderProduct(View itemView) {
        super(itemView);

        mProductThumbnail = (ImageView) itemView.findViewById(R.id.img_produto);
        mTitle = (TextView) itemView.findViewById(R.id.txt_title);
        mQuantity = (TextView) itemView.findViewById(R.id.txt_qtde);
        mCategory = (TextView) itemView.findViewById(R.id.txt_categoria);
        mValue = (TextView) itemView.findViewById(R.id.txt_valor);

        mButtonBuy = (Button) itemView.findViewById(R.id.btn_buy);

    }

}
}
