package model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Writer {

    private Integer id;

    private String name;

    private List<Post> posts;



}
