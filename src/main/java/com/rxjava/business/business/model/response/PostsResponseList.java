package com.rxjava.business.business.model.response;

import lombok.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostsResponseList {
    private List<PostsResponse> postsList;
}
