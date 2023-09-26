package entity

import (
	"github.com/google/uuid"
	"github.com/shopspring/decimal"
	"time"
)

type BankSlip struct {
	Id           uuid.UUID       `json:"id"`
	DueData      time.Time       `json:"due_data"`
	TotalInCents decimal.Decimal `json:"total_in_cents"`
	Customer     string          `json:"customer"`
	Status       string          `json:"status"`
}
