package com.nada.mindtalk.models;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@Entity
public class ChatMessage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String content;
  private String sender;
  @Enumerated(EnumType.STRING)
  private MessageType type;
  @CreationTimestamp
  private LocalDateTime createdAt;

  public enum MessageType {
    CHAT, LEAVE, JOIN
  }
}
