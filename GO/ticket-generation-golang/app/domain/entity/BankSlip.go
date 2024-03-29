package entity

import (
	"github.com/google/uuid"
	"github.com/shopspring/decimal"
	"time"
)

type BankSlip struct {
	Id           uuid.UUID       `json:"id"`
	PaymentDate  time.Time       `json:"payment_date"`
	DueDate      time.Time       `json:"due_date"`
	TotalInCents decimal.Decimal `json:"total_in_cents"`
	Customer     string          `json:"customer"`
	Fine         string          `json:"fine"`
	Status       string          `json:"status"`
}
