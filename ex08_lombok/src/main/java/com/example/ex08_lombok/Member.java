package com.example.ex08_lombok;

import lombok.Data;

// 커맨드 객체
// @Getter
// @Setter
// @AllArgsConstructor
// @NoArgsConstructor
@Data
public class Member {
    private String id;
    private String name;
}
