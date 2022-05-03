package com.musaguzel.acicik

class EklenenUrunler(eklenenUrun: Int , eklenenUrunIsmi: String, eklenenUrunFiyati: String) {

    private var eklenenUrunSayisi:Int
    private var eklenenUrunIsmi = ""
    private var eklenenUrunFiyati = ""

    init{
        this.eklenenUrunSayisi = eklenenUrun
        this.eklenenUrunIsmi = eklenenUrunIsmi
        this.eklenenUrunFiyati = eklenenUrunFiyati
    }
}