package entity

import (
	"github.com/shopspring/decimal"
	"time"
)

type BankSlipRequest struct {
	DueData      time.Time       `json:"due_date" validate:"required" validate:"datetime=2006-01-02"`
	TotalInCents decimal.Decimal `json:"total_in_cents" validate:"required"`
	Customer     string          `json:"customer" validate:"required"`
}
