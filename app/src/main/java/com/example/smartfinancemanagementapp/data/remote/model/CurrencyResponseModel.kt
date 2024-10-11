package com.example.smartfinancemanagementapp.data.remote.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "Tarih_Date", strict = false)
data class CurrencyResponseModel(
    @field:ElementList(name = "Currency", inline = true, required = false)
    var currencies: List<Currency>? = null
) {
    @Root(name = "Currency", strict = false)
    data class Currency(
        @field:Element(name = "Isim", required = false)
        var name: String? = null,

        @field:Attribute(name = "CurrencyCode", required = false)
        var currencyCode: String? = null,

        @field:Element(name = "ForexBuying", required = false)
        var buying: Double? = 0.0,

        @field:Element(name = "ForexSelling", required = false)
        var selling: Double? = 0.0
    )
}
