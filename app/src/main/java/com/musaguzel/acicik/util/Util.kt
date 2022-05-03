package com.musaguzel.acicik.util

import android.content.Context
import android.graphics.Color
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.musaguzel.acicik.R
import java.io.File

    fun ImageView.getImageFromFirebase(url: String?, shimmerDrawable: ShimmerDrawable) {

        val options = RequestOptions()
            .placeholder(shimmerDrawable)
            .error(R.mipmap.ic_launcher_round)

        Glide.with(context)
            .setDefaultRequestOptions(options)
            .load(url)
            .into(this)

    }


    fun placeholderShimmer(context: Context): ShimmerDrawable {

        val shimmer =
            Shimmer.ColorHighlightBuilder()
                .setBaseColor(Color.parseColor("#FFFFFF"))
                .setHighlightColor(Color.parseColor("#E8E8E8"))
                .setDuration(600)
                .setBaseAlpha(0.7F)
                .setHighlightAlpha(0.8f)
                .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                .setAutoStart(true)
                .build()

        return ShimmerDrawable().apply {
            setShimmer(shimmer)
        }

    }


//Tek Sıkıntı Recycler view de 1 olarak verilyior o sayı ordaki textten alınıp verilmesi lazım.

//siparisler ekranı tasarlanıcak ve scrollview ile yapılacak.
//Ana sayfada sepete ekle tıklandığında ürünün uuid si siparişler ekranına aktralılcak.
//Daha sonra gelen uuid ile siparişler ekranına ürün yansıtılacak (+ -) yolu ile de ürün sayısı arttırıp azaltılacak.
//siparişler ekranı sepete eklenen ürünler kısmı recycler view olarak ayarlanacak.

//Otp ekranı güzelleştirilecek tasarımı yapılacak.
//sepet ikonuna tıklandığında sipariş verme ekranına gidelecek.