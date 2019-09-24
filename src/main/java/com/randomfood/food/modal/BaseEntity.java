package com.randomfood.food.modal;

import java.sql.Timestamp;

abstract class BaseEntity {
    private Timestamp createdDate;
    private Timestamp createdBy;
    private Timestamp lastChangeDate;
    private Timestamp lastChangedBy;
}
