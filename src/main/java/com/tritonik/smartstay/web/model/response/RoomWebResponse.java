package com.tritonik.smartstay.web.model.response;

import com.tritonik.smartstay.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomWebResponse {

    private String id;

    private RoomType roomType;

    private Integer price;

    private String imageUrl;
}
