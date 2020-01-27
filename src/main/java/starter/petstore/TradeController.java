package starter.petstore;

import java.util.concurrent.atomic.AtomicLong;

//@RestController
//@Api("trade")
public class TradeController {

    private static AtomicLong tradeIds = new AtomicLong(1);

//    @RequestMapping(value = "/api/trade", method = POST)
//    @ApiOperation("Record a new trade")
//    public Trade recordTrade(@RequestBody Trade newTrade) {
//        return new Trade(tradeIds.incrementAndGet(),
//                         newTrade.getSecurity(),
//                         newTrade.getBuySell(),
//                         newTrade.getQuantity(),
//                         newTrade.getPriceInCents(),
//                newTrade.getQuantity() * newTrade.getPriceInCents() );
//    }
}
