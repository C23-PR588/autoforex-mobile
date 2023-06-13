package com.c23pr588.autoforex.data

import com.c23pr588.autoforex.data.traffic.ListCurrencyItem

object DummiesIDR {
    private var data = arrayOf(
        arrayOf(
            "USD",
            14862.50
        ),
        arrayOf(
            "EUR",
            16087.11
        ),
        arrayOf(
            "GBP",
            18732.22
        ),
        arrayOf(
            "AUD",
            10037.09
        ),
        arrayOf(
            "CAD",
            11194.05
        ),
        arrayOf(
            "CHF",
            16595.06
        ),
        arrayOf(
            "CNH",
            2092.59
        ),
        arrayOf(
            "CNY",
            2096.00
        ),
        arrayOf(
            "DKK",
            2158.80
        ),
        arrayOf(
            "JPY",
            10694.41
        ),
        arrayOf(
            "KRW",
            11.56
        ),
        arrayOf(
            "KWD",
            48608.75
        ),
        arrayOf(
            "LAK",
            0.82
        ),
        arrayOf(
            "MYR",
            3237.32
        ),
        arrayOf(
            "NOK",
            1378.82
        ),
        arrayOf(
            "NZD",
            9111.60
        ),
        arrayOf(
            "PGK",
            4321.44
        ),
        arrayOf(
            "PHP",
            266.46
        ),
        arrayOf(
            "SAR",
            3980.39
        ),
        arrayOf(
            "SEK",
            1379.88
        ),
        arrayOf(
            "SGD",
            11123.14
        ),
        arrayOf(
            "THB",
            431.55
        ),
        arrayOf(
            "VND",
            0.64
        )
    )

    val listCurrencyData: ArrayList<ListCurrencyItem>
        get() {
            val list = ArrayList<ListCurrencyItem>()
            for (i in data) {
                val currency = ListCurrencyItem(i[0].toString(), i[1].toString().toDouble())
                list.add(currency)
            }
            return list
        }
}