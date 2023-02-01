package com.tritonik.smartstay.web.model.request;

import com.tritonik.smartstay.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoomWebRequest {

    private RoomType roomType;

    private Integer price;

    private String imageUrl;
}
