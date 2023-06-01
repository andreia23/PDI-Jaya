package response

import (
	"github.com/google/uuid"
	"github.com/shopspring/decimal"
	"time"
)

type BankSlipResponse struct {
	Id           uuid.UUID
	DueData      time.Time
	TotalInCents decimal.Decimal
	Customer     string
	Status       string
}
