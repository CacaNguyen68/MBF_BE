package com.nguyenanhthu.ptm.authentication.request;

import com.nguyenanhthu.ptm.user.model.ActiveStatus;
import com.nguyenanhthu.ptm.user.model.Gender;
import com.nguyenanhthu.ptm.user.model.LoginStatus;
import com.nguyenanhthu.ptm.user.model.TypeAccount;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequest {

    private String username;
    private String password;
    private String fullName;/*tên đầy đủ*/
    private String phone;/*phone*/
    private String avatar;/*ảnh đại diện*/
    private String position;/*chức vụ*/
    private Integer departmentId;/*phòng đài id*/
    private String note;/*ghi chú*/
    private Gender gender;/*giới tính*/
    private TypeAccount typeAccount;/*loại account*/
    private ActiveStatus activeStatus;/*trạng thái hoạt động*/
}
