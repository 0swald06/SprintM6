package com.example.sprintm6.Modelo

import com.example.sprintm6.Modelo.Local.Database.PhonesDetailEntity
import com.example.sprintm6.Modelo.Local.Database.PhonesEntity
import com.example.sprintm6.Modelo.Remote.FromInternet.Phones
import com.example.sprintm6.Modelo.Remote.FromInternet.PhonesDetail

fun fromInternetToPhonesEntity(phonesList:List<Phones>):List<PhonesEntity>{

    return phonesList.map {

        PhonesEntity(
            id=it.id,
            name=it.name,
            price=it.price,
            image=it.image,

        )
    }

}
fun fromInternetToPhonesDetailEntity(phones:PhonesDetail):PhonesDetailEntity{

    return  PhonesDetailEntity(
        id=phones.id,
        name=phones.name,
        price=phones.price,
        image=phones.image,

    )
}