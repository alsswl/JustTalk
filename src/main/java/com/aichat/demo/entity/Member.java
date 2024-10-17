package com.aichat.demo.entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long memberId;

  private String name;
  private String email;
  private String password;
  @Lob
  private String Content1 = "";
  @Lob
  private String Content2 = "";
  @Lob
  private String Content3 = "";
  @Lob
  private String Content4 = "";

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<CommunityPost> communityPosts;

  public Member(Object o, String email, String password, String name, ArrayList<Report> reports, ArrayList<CommunityPost> communityPosts) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.communityPosts = communityPosts;
  }

  public Member() {

  }

  public boolean checkPassword(String password){
    return this.password.equals(password);
  }

  public void validatePassword(String password) {
    if (password.length() < 8) {
      throw new RuntimeException("비밀번호는 8자 이상의 문자여야 합니다.");
    }
  }

}
