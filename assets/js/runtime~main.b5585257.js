(()=>{"use strict";var e,r,t,a,o,n={},d={};function c(e){var r=d[e];if(void 0!==r)return r.exports;var t=d[e]={id:e,loaded:!1,exports:{}};return n[e].call(t.exports,t,t.exports,c),t.loaded=!0,t.exports}c.m=n,c.c=d,e=[],c.O=(r,t,a,o)=>{if(!t){var n=1/0;for(b=0;b<e.length;b++){t=e[b][0],a=e[b][1],o=e[b][2];for(var d=!0,f=0;f<t.length;f++)(!1&o||n>=o)&&Object.keys(c.O).every((e=>c.O[e](t[f])))?t.splice(f--,1):(d=!1,o<n&&(n=o));if(d){e.splice(b--,1);var i=a();void 0!==i&&(r=i)}}return r}o=o||0;for(var b=e.length;b>0&&e[b-1][2]>o;b--)e[b]=e[b-1];e[b]=[t,a,o]},c.n=e=>{var r=e&&e.__esModule?()=>e.default:()=>e;return c.d(r,{a:r}),r},t=Object.getPrototypeOf?e=>Object.getPrototypeOf(e):e=>e.__proto__,c.t=function(e,a){if(1&a&&(e=this(e)),8&a)return e;if("object"==typeof e&&e){if(4&a&&e.__esModule)return e;if(16&a&&"function"==typeof e.then)return e}var o=Object.create(null);c.r(o);var n={};r=r||[null,t({}),t([]),t(t)];for(var d=2&a&&e;"object"==typeof d&&!~r.indexOf(d);d=t(d))Object.getOwnPropertyNames(d).forEach((r=>n[r]=()=>e[r]));return n.default=()=>e,c.d(o,n),o},c.d=(e,r)=>{for(var t in r)c.o(r,t)&&!c.o(e,t)&&Object.defineProperty(e,t,{enumerable:!0,get:r[t]})},c.f={},c.e=e=>Promise.all(Object.keys(c.f).reduce(((r,t)=>(c.f[t](e,r),r)),[])),c.u=e=>"assets/js/"+({27:"e1262149",48:"a94703ab",61:"1f391b9e",98:"a7bd4aaa",134:"393be207",168:"ca39b904",235:"a7456010",292:"2a2a0c40",379:"5407da49",401:"17896441",411:"34d2ee81",504:"25b7353d",581:"4fa98de5",583:"1df93b7f",625:"1745b6e0",647:"5e95c892",683:"dfcda264",742:"aba21aa0",746:"ebfcd236",820:"8cc644be",841:"4e04d029",845:"499eeb00",908:"c9a23494",938:"7df7b90f",973:"d44d2435"}[e]||e)+"."+{27:"0290c915",48:"72fd82c9",61:"c284202f",98:"d48c0255",134:"b533d46b",168:"7aa066eb",235:"4125e676",237:"861184df",292:"6b447103",379:"e4ecbed3",401:"a3f83e50",411:"6025d89b",504:"0e4863ad",581:"509e96e7",583:"36f02a3f",625:"e47a007d",647:"e441df02",658:"61007190",683:"c547b603",742:"c36cad5d",746:"b244740e",820:"f084f009",841:"6b311557",845:"708348ae",908:"3d9610dc",938:"2d7cf852",973:"c57820a9"}[e]+".js",c.miniCssF=e=>{},c.g=function(){if("object"==typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(e){if("object"==typeof window)return window}}(),c.o=(e,r)=>Object.prototype.hasOwnProperty.call(e,r),a={},o="react-native-screenguard-docs:",c.l=(e,r,t,n)=>{if(a[e])a[e].push(r);else{var d,f;if(void 0!==t)for(var i=document.getElementsByTagName("script"),b=0;b<i.length;b++){var u=i[b];if(u.getAttribute("src")==e||u.getAttribute("data-webpack")==o+t){d=u;break}}d||(f=!0,(d=document.createElement("script")).charset="utf-8",d.timeout=120,c.nc&&d.setAttribute("nonce",c.nc),d.setAttribute("data-webpack",o+t),d.src=e),a[e]=[r];var l=(r,t)=>{d.onerror=d.onload=null,clearTimeout(s);var o=a[e];if(delete a[e],d.parentNode&&d.parentNode.removeChild(d),o&&o.forEach((e=>e(t))),r)return r(t)},s=setTimeout(l.bind(null,void 0,{type:"timeout",target:d}),12e4);d.onerror=l.bind(null,d.onerror),d.onload=l.bind(null,d.onload),f&&document.head.appendChild(d)}},c.r=e=>{"undefined"!=typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},c.p="/react-native-screenguard/",c.gca=function(e){return e={17896441:"401",e1262149:"27",a94703ab:"48","1f391b9e":"61",a7bd4aaa:"98","393be207":"134",ca39b904:"168",a7456010:"235","2a2a0c40":"292","5407da49":"379","34d2ee81":"411","25b7353d":"504","4fa98de5":"581","1df93b7f":"583","1745b6e0":"625","5e95c892":"647",dfcda264:"683",aba21aa0:"742",ebfcd236:"746","8cc644be":"820","4e04d029":"841","499eeb00":"845",c9a23494:"908","7df7b90f":"938",d44d2435:"973"}[e]||e,c.p+c.u(e)},(()=>{var e={354:0,869:0};c.f.j=(r,t)=>{var a=c.o(e,r)?e[r]:void 0;if(0!==a)if(a)t.push(a[2]);else if(/^(354|869)$/.test(r))e[r]=0;else{var o=new Promise(((t,o)=>a=e[r]=[t,o]));t.push(a[2]=o);var n=c.p+c.u(r),d=new Error;c.l(n,(t=>{if(c.o(e,r)&&(0!==(a=e[r])&&(e[r]=void 0),a)){var o=t&&("load"===t.type?"missing":t.type),n=t&&t.target&&t.target.src;d.message="Loading chunk "+r+" failed.\n("+o+": "+n+")",d.name="ChunkLoadError",d.type=o,d.request=n,a[1](d)}}),"chunk-"+r,r)}},c.O.j=r=>0===e[r];var r=(r,t)=>{var a,o,n=t[0],d=t[1],f=t[2],i=0;if(n.some((r=>0!==e[r]))){for(a in d)c.o(d,a)&&(c.m[a]=d[a]);if(f)var b=f(c)}for(r&&r(t);i<n.length;i++)o=n[i],c.o(e,o)&&e[o]&&e[o][0](),e[o]=0;return c.O(b)},t=self.webpackChunkreact_native_screenguard_docs=self.webpackChunkreact_native_screenguard_docs||[];t.forEach(r.bind(null,0)),t.push=r.bind(null,t.push.bind(t))})()})();