package com.clevershuttle.carfleet.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@Data
public class AbstractEntity  implements Serializable {
  protected static final int DEFAULT_SCALE = 2;
  @Serial
  private static final long serialVersionUID = -3733796182675284472L;
  /**
   * Primary key.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected long id = 0;
  /**
   * Auto-incrementing version (integer) column.  Incremented automatically
   * on each UPDATE.  Can be used for optimistic locking.
   */
  @Version
  @Column(name = "version")
  int version = 0;
  @CreatedDate
  @Column(name ="created-at",columnDefinition = "TIMESTAMP")
  Instant createdAt;
  @LastModifiedDate
  @Column(name ="last-updated-at",columnDefinition = "TIMESTAMP")
  Instant lastUpdatedAt;
  @Column(name ="active")
  boolean active= true;
}
