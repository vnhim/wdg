package com.wedoogift.challenge.infraweb.model.user;

import com.wedoogift.challenge.infraweb.model.Request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UserListRequest extends Request {
    @NotNull
    @Min(0)
    private Integer page;
    @NotNull
    @Min(1)
    @Max(20)
    private Integer count;

    public UserListRequest() {
    }

    public UserListRequest(Integer page, Integer count) {
        this.page = page;
        this.count = count;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getCount() {
        return count;
    }
}
