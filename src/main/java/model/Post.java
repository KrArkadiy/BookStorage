package model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Post {
    private Integer id;

    private String content;

    private Long created;

    private Long updated;

    private List<Label> labels;
}
