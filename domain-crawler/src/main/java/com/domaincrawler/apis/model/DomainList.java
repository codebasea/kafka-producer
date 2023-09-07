package com.domaincrawler.apis.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
public class DomainList {

  List<Domain> domains;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DomainList that = (DomainList) o;
    return Objects.equals(domains, that.domains);
  }

  @Override
  public int hashCode() {
    return Objects.hash(domains);
  }

  public DomainList() {
  }

  public DomainList(List<Domain> domains) {
    this.domains = domains;
  }
}
