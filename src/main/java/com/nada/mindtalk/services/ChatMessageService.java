package com.nada.mindtalk.services;

import com.nada.mindtalk.models.ChatMessage;
import com.nada.mindtalk.repositories.ChatMessageRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

  private final ChatMessageRepository chatMessageRepository;

  public ChatMessage addMessage(ChatMessage message) {
    return chatMessageRepository.save(message);
  }

  public List<ChatMessage> fetchChatHistory() {
    return chatMessageRepository.findAll(Sort.by(Direction.ASC,"id"));
  }
}
