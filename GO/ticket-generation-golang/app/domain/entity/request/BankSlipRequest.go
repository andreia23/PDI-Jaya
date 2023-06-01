package request

import (
	"github.com/shopspring/decimal"
	"time"
)

type BankSlipRequest struct {
	DueData      time.Time
	TotalInCents decimal.Decimal
	Customer     string
}
