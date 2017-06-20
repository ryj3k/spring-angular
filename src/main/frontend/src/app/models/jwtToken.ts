import {Serializable} from './serializable.base';
export class JwtToken extends Serializable {
  token: string;
  refreshToken: string;

}
