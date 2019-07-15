package com.example.organicworld.model

import com.example.organicworld.model.Entity.OrganicDetailDTO
import com.example.organicworld.model.Entity.OrganicSearchDTO
import com.example.organicworld.model.Service.OrganicService
import io.reactivex.Single


class OrganicSearchModel {
    val organicApiServe by lazy {
        OrganicService.create()
    }

fun fetchOrganicList(keyword: String): Single<OrganicSearchDTO.Organics> {
    return organicApiServe.searchOrganic("15da9a82656696d6c1b16e258c8fdb17", keyword )
}

}

class OrganicDetailModel {
    val organicApiServe by lazy {
        OrganicService.create()
    }

    fun getOrganicDetails(organicId: String): Single<OrganicDetailDTO.Result> {
        return organicApiServe.getOrganic("15da9a82656696d6c1b16e258c8fdb17", organicId)
    }
}

