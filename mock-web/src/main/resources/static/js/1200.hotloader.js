(self.webpackChunk=self.webpackChunk||[]).push([[1200],{81200:(e,t,n)=>{!function(e){"use strict";e.defineMode("solr",(function(){var e=/[^\s\|\!\+\-\*\?\~\^\&\:\(\)\[\]\{\}\"\\]/,t=/[\|\!\+\-\*\?\~\^\&]/,n=/^(OR|AND|NOT|TO)$/i;function r(t){return function(r,i){for(var u=t;(t=r.peek())&&null!=t.match(e);)u+=r.next();return i.tokenize=o,n.test(u)?"operator":function(e){return parseFloat(e).toString()===e}(u)?"number":":"==r.peek()?"field":"string"}}function o(n,i){var u,a,s=n.next();return'"'==s?i.tokenize=(a=s,function(e,t){for(var n,r=!1;null!=(n=e.next())&&(n!=a||r);)r=!r&&"\\"==n;return r||(t.tokenize=o),"string"}):t.test(s)?i.tokenize=(u=s,function(e,t){var n="operator";return"+"==u?n+=" positive":"-"==u?n+=" negative":"|"==u?e.eat(/\|/):"&"==u?e.eat(/\&/):"^"==u&&(n+=" boost"),t.tokenize=o,n}):e.test(s)&&(i.tokenize=r(s)),i.tokenize!=o?i.tokenize(n,i):null}return{startState:function(){return{tokenize:o}},token:function(e,t){return e.eatSpace()?null:t.tokenize(e,t)}}})),e.defineMIME("text/x-solr","solr")}(n(25747))}}]);
//# sourceMappingURL=1200.hotloader.js.map