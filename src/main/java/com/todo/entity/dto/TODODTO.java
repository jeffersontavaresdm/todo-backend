package com.todo.entity.dto;

import java.time.OffsetDateTime;

public record TODODTO(Long id, String name, OffsetDateTime startDate, OffsetDateTime endDate) {}